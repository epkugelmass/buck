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

package com.facebook.buck.features.python;

import com.facebook.buck.core.model.BuildTarget;
import com.facebook.buck.core.model.Flavor;
import com.facebook.buck.features.python.toolchain.PythonEnvironment;
import com.facebook.buck.features.python.toolchain.PythonPlatform;
import com.google.common.collect.ImmutableList;
import java.util.Optional;

public class TestPythonPlatform implements PythonPlatform {

  private final Flavor flavor;
  private final PythonEnvironment pythonEnvironment;
  private final Optional<BuildTarget> cxxLibrary;

  public TestPythonPlatform(
      Flavor flavor, PythonEnvironment pythonEnvironment, Optional<BuildTarget> cxxLibrary) {
    this.flavor = flavor;
    this.pythonEnvironment = pythonEnvironment;
    this.cxxLibrary = cxxLibrary;
  }

  @Override
  public Flavor getFlavor() {
    return flavor;
  }

  @Override
  public PythonEnvironment getEnvironment() {
    return pythonEnvironment;
  }

  @Override
  public Optional<BuildTarget> getCxxLibrary() {
    return cxxLibrary;
  }

  @Override
  public ImmutableList<String> getInplaceBinaryInterpreterFlags() {
    return ImmutableList.of("-Es");
  }
}
