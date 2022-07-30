package com.deviget.core;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Optional;
import java.util.OptionalInt;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class CoreConstants {

  public static final String BASE_URL = "BASE_URL";
  public static final String SOL = "sol";
  public static final String EARTH_DATE = "earth_date";
  public static final String CAMERA = "camera";
  public static final String PAGE = "page";
  public static final String API_KEY_NAME = "api_key";
  public static final String PHOTOS = "photos";
  public static final OptionalInt EMPTY_INT = OptionalInt.empty();
  public static final Optional<String> EMPTY_STR = Optional.empty();
  public static final OptionalInt THOUSAND = OptionalInt.of(1000);
  public static final OptionalInt TEN = OptionalInt.of(10);
  public static final Integer STATUS_CODE_OK = 200;
  public static final OptionalInt ZERO = OptionalInt.of(0);
  public static final String API_KEY = Dotenv.load().get("API_KEY");
  public static final String ROVERS_CURIOSITY = Dotenv.load().get("ROVERS_CURIOSITY");
  public static final String MANIFESTS_CURIOSITY = Dotenv.load().get("MANIFESTS_CURIOSITY");
  public static final Optional<String> EARTH_DATE_VALUE = Optional.of("2015-05-30");
  public static final String PHOTOS_BY_CURIOSITY_MESSAGE = "first 10 Mars photos made by 'Curiosity' on 1000 Martian sol";
  public static final String RESPONSE_CODE_MESSAGE = "Response status code should be 200";
}
