package com.deviget.core;

public abstract class ValidationsHelper {

  public static String getValueShouldBeEqualValidation(Object actual, Object expected, String fieldLabel) {
    return fieldLabel + " actual: [" + actual + "], expected: [" + expected + "] should be equal";
  }

  public static String getValueShouldBeGreaterThanValidation(Integer actual, Integer other, String fieldLabel) {
    return fieldLabel + " value [" + actual + "] should be greater than [" + other + "]";
  }
}