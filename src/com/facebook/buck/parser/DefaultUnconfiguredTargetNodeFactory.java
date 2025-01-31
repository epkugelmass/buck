/*
 * Copyright 2018-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.parser;

import com.facebook.buck.core.cell.Cell;
import com.facebook.buck.core.description.BaseDescription;
import com.facebook.buck.core.exceptions.DependencyStack;
import com.facebook.buck.core.model.AbstractRuleType;
import com.facebook.buck.core.model.RuleType;
import com.facebook.buck.core.model.UnconfiguredBuildTargetView;
import com.facebook.buck.core.model.targetgraph.impl.ImmutableUnconfiguredTargetNode;
import com.facebook.buck.core.model.targetgraph.raw.UnconfiguredTargetNode;
import com.facebook.buck.core.rules.knowntypes.KnownRuleTypes;
import com.facebook.buck.core.rules.knowntypes.provider.KnownRuleTypesProvider;
import com.facebook.buck.core.select.SelectorList;
import com.facebook.buck.parser.api.ProjectBuildFileParser;
import com.facebook.buck.parser.function.BuckPyFunction;
import com.facebook.buck.rules.visibility.VisibilityPattern;
import com.facebook.buck.rules.visibility.parser.VisibilityPatterns;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

/**
 * Creates {@link UnconfiguredTargetNode} instances from raw data coming in form the {@link
 * ProjectBuildFileParser}.
 */
public class DefaultUnconfiguredTargetNodeFactory implements UnconfiguredTargetNodeFactory {

  private final KnownRuleTypesProvider knownRuleTypesProvider;
  private final BuiltTargetVerifier builtTargetVerifier;

  public DefaultUnconfiguredTargetNodeFactory(
      KnownRuleTypesProvider knownRuleTypesProvider, BuiltTargetVerifier builtTargetVerifier) {
    this.knownRuleTypesProvider = knownRuleTypesProvider;
    this.builtTargetVerifier = builtTargetVerifier;
  }

  @Override
  public UnconfiguredTargetNode create(
      Cell cell,
      Path buildFile,
      UnconfiguredBuildTargetView target,
      DependencyStack dependencyStack,
      Map<String, Object> rawAttributes) {
    KnownRuleTypes knownRuleTypes = knownRuleTypesProvider.get(cell);
    RuleType ruleType = parseRuleTypeFromRawRule(knownRuleTypes, rawAttributes);

    if (ruleType.getKind() == AbstractRuleType.Kind.CONFIGURATION) {
      assertRawTargetNodeAttributesNotConfigurable(target, rawAttributes);
    }

    // Because of the way that the parser works, we know this can never return null.
    BaseDescription<?> description = knownRuleTypes.getDescription(ruleType);

    builtTargetVerifier.verifyBuildTarget(
        cell, ruleType, buildFile, target, description, rawAttributes);

    ImmutableSet<VisibilityPattern> visibilityPatterns =
        VisibilityPatterns.createFromStringList(
            cell.getCellPathResolver(),
            "visibility",
            rawAttributes.get("visibility"),
            target.getData());
    ImmutableSet<VisibilityPattern> withinViewPatterns =
        VisibilityPatterns.createFromStringList(
            cell.getCellPathResolver(),
            "within_view",
            rawAttributes.get("within_view"),
            target.getData());

    return ImmutableUnconfiguredTargetNode.of(
        target.getData(),
        ruleType,
        ImmutableMap.copyOf(rawAttributes),
        visibilityPatterns,
        withinViewPatterns);
  }

  private static RuleType parseRuleTypeFromRawRule(
      KnownRuleTypes knownRuleTypes, Map<String, Object> attributes) {
    String type =
        (String) Objects.requireNonNull(attributes.get(BuckPyFunction.TYPE_PROPERTY_NAME));
    return knownRuleTypes.getRuleType(type);
  }

  private void assertRawTargetNodeAttributesNotConfigurable(
      UnconfiguredBuildTargetView buildTarget, Map<String, Object> rawTargetNodeAttributes) {
    for (Map.Entry<String, ?> entry : rawTargetNodeAttributes.entrySet()) {
      Preconditions.checkState(
          !(entry.getValue() instanceof SelectorList),
          "Attribute %s cannot be configurable in %s",
          entry.getKey(),
          buildTarget);
    }
  }
}
