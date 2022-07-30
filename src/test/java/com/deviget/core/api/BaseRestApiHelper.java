package com.deviget.core.api;

import com.deviget.core.ExpectationHelper;

import static com.deviget.core.api.TestContext.CONTEXT;

public class BaseRestApiHelper {

  public static void verifyValue(Object expectedText, Object actualText, String elementName) {
    ExpectationHelper.verifyValue(expectedText, actualText, elementName);
  }

  public static void verifyValueGreaterThan(Integer actual, Integer other, String elementName) {
    ExpectationHelper.verifyValueGreaterThan(actual, other, elementName);
  }

  public TestContext testContext() {
    return CONTEXT;
  }
}
