package com.deviget.core;

import com.deviget.core.logger.StepLogger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpectationHelper {

  public static void verifyValue(Object expectedText, Object actualText, String elementName) {
    String message = ValidationsHelper.getValueShouldBeEqualValidation(actualText, expectedText, elementName);
    StepLogger.subVerification(message);
    assertEquals(expectedText, actualText, message);
  }

  public static void verifyValueGreaterThan(Integer actual, Integer other, String elementName) {
    String message = ValidationsHelper.getValueShouldBeGreaterThanValidation(actual, other, elementName);
    StepLogger.subVerification(message);
    assertThat(actual).isGreaterThan(other);
  }
}
