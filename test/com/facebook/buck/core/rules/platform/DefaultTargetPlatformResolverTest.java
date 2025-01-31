/*
 * Copyright 2019-present Facebook, Inc.
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
package com.facebook.buck.core.rules.platform;

import static org.junit.Assert.assertEquals;

import com.facebook.buck.core.exceptions.DependencyStack;
import com.facebook.buck.core.exceptions.HumanReadableException;
import com.facebook.buck.core.model.BuildTarget;
import com.facebook.buck.core.model.ConfigurationBuildTargetFactoryForTests;
import com.facebook.buck.core.model.ConfigurationForConfigurationTargets;
import com.facebook.buck.core.model.TargetConfiguration;
import com.facebook.buck.core.model.UnconfiguredTargetConfiguration;
import com.facebook.buck.core.model.impl.ImmutableRuleBasedTargetConfiguration;
import com.facebook.buck.core.model.platform.Platform;
import com.facebook.buck.core.model.platform.impl.ConstraintBasedPlatform;
import com.facebook.buck.core.model.platform.impl.UnconfiguredPlatform;
import com.facebook.buck.core.rules.config.ConfigurationRuleResolver;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterables;
import java.util.Optional;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DefaultTargetPlatformResolverTest {

  @Rule public ExpectedException thrown = ExpectedException.none();

  @Test
  public void returnCorrectPlatformForEmptyTargetConfiguration() {
    Platform emptyTargetConfigurationPlatform = UnconfiguredPlatform.INSTANCE;
    DefaultTargetPlatformResolver targetPlatformResolver =
        new DefaultTargetPlatformResolver(
            new RuleBasedTargetPlatformResolver(
                new RuleBasedPlatformResolver(
                    (target, dependencyStack) -> null, new ThrowingConstraintResolver())));

    assertEquals(
        emptyTargetConfigurationPlatform,
        targetPlatformResolver.getTargetPlatform(
            UnconfiguredTargetConfiguration.INSTANCE, DependencyStack.root()));
  }

  @Test
  public void returnCorrectPlatformForConfigurationForConfigurationTargets() {
    Platform emptyTargetConfigurationPlatform = UnconfiguredPlatform.INSTANCE;
    DefaultTargetPlatformResolver targetPlatformResolver =
        new DefaultTargetPlatformResolver(
            new RuleBasedTargetPlatformResolver(
                new RuleBasedPlatformResolver(
                    (target, dependencyStack) -> null, new ThrowingConstraintResolver())));

    assertEquals(
        emptyTargetConfigurationPlatform,
        targetPlatformResolver.getTargetPlatform(
            ConfigurationForConfigurationTargets.INSTANCE, DependencyStack.root()));
  }

  @Test
  public void returnCorrectPlatformForDefaultTargetConfiguration() {
    BuildTarget platformTarget =
        ConfigurationBuildTargetFactoryForTests.newInstance("//platform:platform");
    BuildTarget constraintValue =
        ConfigurationBuildTargetFactoryForTests.newInstance("//constraint:value");
    BuildTarget constraintSetting =
        ConfigurationBuildTargetFactoryForTests.newInstance("//constraint:setting");

    ConfigurationRuleResolver configurationRuleResolver =
        (buildTarget, dependencyStack) -> {
          if (buildTarget.equals(platformTarget)) {
            return PlatformRule.of(
                platformTarget,
                "platform",
                ImmutableSortedSet.of(constraintValue),
                ImmutableSortedSet.of());
          }
          if (buildTarget
              .getUnconfiguredBuildTargetView()
              .equals(constraintValue.getUnconfiguredBuildTargetView())) {
            return new ConstraintValueRule(constraintValue, "value", constraintSetting);
          }
          if (buildTarget.equals(constraintSetting)) {
            return new ConstraintSettingRule(constraintValue, "value");
          }
          throw new IllegalArgumentException("Invalid build target: " + buildTarget);
        };

    RuleBasedTargetPlatformResolver ruleBasedTargetPlatformResolver =
        new RuleBasedTargetPlatformResolver(
            new RuleBasedPlatformResolver(
                configurationRuleResolver,
                new RuleBasedConstraintResolver(configurationRuleResolver)));

    DefaultTargetPlatformResolver targetPlatformResolver =
        new DefaultTargetPlatformResolver(ruleBasedTargetPlatformResolver);

    ConstraintBasedPlatform platform =
        (ConstraintBasedPlatform)
            targetPlatformResolver.getTargetPlatform(
                ImmutableRuleBasedTargetConfiguration.of(platformTarget), DependencyStack.root());

    assertEquals("//platform:platform", platform.toString());
    assertEquals(1, platform.getConstraintValues().size());
    assertEquals(
        constraintValue, Iterables.getOnlyElement(platform.getConstraintValues()).getBuildTarget());
  }

  @Test
  public void requestingPlatformForWrongTypeThrowsException() {
    DefaultTargetPlatformResolver targetPlatformResolver =
        new DefaultTargetPlatformResolver(
            new RuleBasedTargetPlatformResolver(
                new RuleBasedPlatformResolver(
                    (target, dependencyStack) -> null, new ThrowingConstraintResolver())));

    thrown.expect(HumanReadableException.class);
    thrown.expectMessage("Cannot determine target platform for configuration:");

    targetPlatformResolver.getTargetPlatform(
        new TargetConfiguration() {
          @Override
          public Optional<BuildTarget> getConfigurationTarget() {
            return Optional.empty();
          }
        },
        DependencyStack.root());
  }
}
