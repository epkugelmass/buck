/*
 * Copyright 2012-present Facebook, Inc.
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

package com.facebook.buck.core.exceptions;

import javax.annotation.Nullable;

/**
 * Exception with an error message that can sensibly be displayed to the user without a stacktrace.
 * This exception is meant only to be caught at the top level of the application.
 */
public class HumanReadableException extends RuntimeException
    implements ExceptionWithHumanReadableMessage {

  private final String humanReadableErrorMessage;
  private final DependencyStack dependencyStack;

  public HumanReadableException(
      DependencyStack dependencyStack, String humanReadableFormatString, Object... args) {
    this(dependencyStack, String.format(humanReadableFormatString, args));
  }

  public HumanReadableException(DependencyStack dependencyStack, String humanReadableErrorMessage) {
    this(null, dependencyStack, humanReadableErrorMessage);
  }

  public HumanReadableException(
      @Nullable Throwable cause,
      DependencyStack dependencyStack,
      String humanReadableErrorMessage) {
    super(humanReadableErrorMessage, cause);
    this.humanReadableErrorMessage = humanReadableErrorMessage;
    this.dependencyStack = dependencyStack;
  }

  public HumanReadableException(
      @Nullable Throwable cause,
      DependencyStack dependencyStack,
      String humanReadableFormatString,
      Object... args) {
    this(cause, dependencyStack, String.format(humanReadableFormatString, args));
  }

  public HumanReadableException(String humanReadableFormatString, Object... args) {
    this(String.format(humanReadableFormatString, args));
  }

  public HumanReadableException(String humanReadableErrorMessage) {
    this((Throwable) null /* cause */, humanReadableErrorMessage);
  }

  public HumanReadableException(@Nullable Throwable cause, String humanReadableErrorMessage) {
    super(humanReadableErrorMessage, cause);
    this.humanReadableErrorMessage = humanReadableErrorMessage;
    this.dependencyStack = DependencyStack.root();
  }

  public HumanReadableException(
      @Nullable Throwable cause, String humanReadableFormatString, Object... args) {
    this(cause, String.format(humanReadableFormatString, args));
  }

  public HumanReadableException(ExceptionWithHumanReadableMessage e) {
    this(
        (Throwable) ((e instanceof Throwable) ? e : null),
        e.getDependencyStack(),
        e.getHumanReadableErrorMessage());
  }

  @Override
  public String getHumanReadableErrorMessage() {
    return humanReadableErrorMessage;
  }

  @Override
  public DependencyStack getDependencyStack() {
    return dependencyStack;
  }
}
