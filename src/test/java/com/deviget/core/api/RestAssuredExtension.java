package com.deviget.core.api;

import com.deviget.core.EndPointHelper;
import com.deviget.core.logger.StepLogger;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RedirectConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredExtension {

  private final RequestSpecification requestSpec;

  private RestAssuredExtension() {
    RequestSpecBuilder builder = new RequestSpecBuilder();
    builder.setBaseUri(EndPointHelper.BASE_URL);
    builder.setContentType(ContentType.JSON);
    RestAssured.config = RestAssured.config().redirect(RedirectConfig.redirectConfig().followRedirects(false));
    RestAssured.useRelaxedHTTPSValidation();
    requestSpec = given().spec(builder.build());
  }

  @NotNull
  @Contract(" -> new")
  public static RestAssuredExtension getInstance() {
    return new RestAssuredExtension();
  }

  public Response get(String url, Map<String, String> pathParams) {
    StepLogger.subStep("Make a GET request to: " + EndPointHelper.BASE_URL + url);
    try {
      return requestSpec.queryParams(pathParams).get(new URI(url)).thenReturn();
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  public Response post(String url, Map<String, String> body) {
    requestSpec.body(body);
    StepLogger.subStep("Make a POST request to: " + EndPointHelper.BASE_URL + url);
    return requestSpec.post(url).thenReturn();
  }

  public Response delete(String url, Map<String, String> pathParams) {
    requestSpec.pathParams(pathParams);
    StepLogger.subStep("Make a DELETE request to: " + EndPointHelper.BASE_URL + url);
    return requestSpec.delete(url).thenReturn();
  }
}