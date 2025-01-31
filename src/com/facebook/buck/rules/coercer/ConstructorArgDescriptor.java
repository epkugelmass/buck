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
package com.facebook.buck.rules.coercer;

import com.facebook.buck.core.description.arg.DataTransferObject;
import com.facebook.buck.core.model.BuildTarget;
import com.facebook.buck.core.util.immutables.BuckStyleValue;
import com.google.common.collect.ImmutableMap;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/** Class that contains the values needed to build a DescriptionArg */
@BuckStyleValue
public abstract class ConstructorArgDescriptor<T extends DataTransferObject> {

  /** Reified {@code <T>} */
  public abstract Class<T> constructorArgClass();

  /**
   * Get the builder for a constructor arg.
   *
   * <p>This is either the ImmutableValue.Builder class for {@code T}, or {@link
   * com.facebook.buck.core.starlark.rule.SkylarkDescriptionArg}
   */
  public abstract Supplier<Object> getBuilderFactory();

  /** The param infos needed to populate this object */
  public abstract ImmutableMap<String, ParamInfo> getParamInfos();

  /**
   * A function that takes the result of {@link #getBuilderFactory()}}, and calls its 'build'
   * function to return description args of type {@code T}
   */
  protected abstract BiFunction<Object, BuildTarget, T> getBuildFunction();

  /**
   * Builds the partially constructed object returned by {@link #getBuilderFactory()}
   *
   * @return A fully constructed ConstructorArg of type {@code T} obtained by applying {@link
   *     #getBuildFunction()} to {@link #getBuilderFactory()}
   */
  public T build(Object builder, BuildTarget buildTarget) {
    return getBuildFunction().apply(builder, buildTarget);
  }
}
