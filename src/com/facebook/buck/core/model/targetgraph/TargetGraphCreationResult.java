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

package com.facebook.buck.core.model.targetgraph;

import com.facebook.buck.core.model.BuildTarget;
import com.facebook.buck.core.util.immutables.BuckStyleValue;
import com.google.common.collect.ImmutableSet;
import org.immutables.value.Value;

/** Contains information produced as a result of the phase of target graph creation. */
@Value.Immutable(builder = false, copy = true, prehash = true)
@BuckStyleValue
public abstract class TargetGraphCreationResult {

  /**
   * A graph of transitive dependencies of the top level targets from {@link #getBuildTargets()}.
   */
  public abstract TargetGraph getTargetGraph();

  /**
   * Top level targets of the target graph.
   *
   * <p>A top level target is a target requested by a client during target graph creation request.
   *
   * <p>Note that top level targets are not equal to the nodes without incoming edges. A top level
   * target can be in a transitive dependencies of another top level target and can have incoming
   * edges.
   */
  public abstract ImmutableSet<BuildTarget> getBuildTargets();

  /** Copies this object with replacing the target graph. */
  public abstract TargetGraphCreationResult withTargetGraph(TargetGraph targetGraph);

  /** Copies this object with replacing the top level targets. */
  public abstract TargetGraphCreationResult withBuildTargets(
      Iterable<? extends BuildTarget> elements);
}
