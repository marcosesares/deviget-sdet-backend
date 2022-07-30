package com.deviget.mars;

import com.deviget.core.api.MarsPhotosRestApiHelper;
import com.deviget.core.logger.StepLogger;
import com.deviget.model.MarsRoverPhoto;
import com.deviget.model.PhotoManifest;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Consumer;

import static com.deviget.core.CoreConstants.*;
import static com.deviget.core.api.BaseRestApiHelper.verifyValue;
import static com.deviget.core.api.BaseRestApiHelper.verifyValueGreaterThan;

@Execution(ExecutionMode.SAME_THREAD)
@Tag("component")
public class MarsPhotosByCuriosityTest {

  @Feature("Retrieve the first 10 Mars photos made by 'Curiosity' on 1000 Martian sol.")
  @Test
  public void retrieve_first_10_mars_photos_by_curiosity_on_1000_martian_sol() {
    StepLogger.setCaseId(1001, null);
    StepLogger.step("Retrieve the first 10 Mars photos made by 'Curiosity' on 1000 Martian sol.");
    MarsPhotosRestApiHelper helper = new MarsPhotosRestApiHelper();
    List<MarsRoverPhoto> photos = helper.retrieveMarsPhotos(ZERO, TEN, THOUSAND, EMPTY_STR, EMPTY_STR, EMPTY_INT);

    StepLogger.verification("Verify API response.");
    verifyValue(helper.testContext().getResponse().getStatusCode(), STATUS_CODE_OK, RESPONSE_CODE_MESSAGE);
    verifyValue(photos.size(), TEN.getAsInt(), PHOTOS_BY_CURIOSITY_MESSAGE);
  }

  @Feature("Retrieve the first 10 Mars photos made by 'Curiosity' on Earth date equal to 1000 Martian sol.")
  @Test
  public void retrieve_first_10_mars_photos_by_curiosity_on_earth_date_equal_to_1000_martian_sol() {
    StepLogger.setCaseId(1002, null);
    StepLogger.step("Retrieve the first 10 Mars photos made by 'Curiosity' on Earth date equal to 1000 Martian sol.");
    MarsPhotosRestApiHelper helper = new MarsPhotosRestApiHelper();
    List<MarsRoverPhoto> photos = helper.retrieveMarsPhotos(ZERO, TEN, EMPTY_INT, EARTH_DATE_VALUE, EMPTY_STR, EMPTY_INT);

    StepLogger.verification("Verify API response.");
    verifyValue(helper.testContext().getResponse().getStatusCode(), STATUS_CODE_OK, RESPONSE_CODE_MESSAGE);
    verifyValue(photos.size(), TEN.getAsInt(), PHOTOS_BY_CURIOSITY_MESSAGE);
  }

  @Feature("Retrieve and compare the first 10 Mars photos made by 'Curiosity' on 1000 sol and on Earth date equal to" +
          " 1000 Martian sol.")
  @Test
  public void comparePhotosBySolAndEarth() {
    StepLogger.setCaseId(1003, null);
    StepLogger.step("Retrieve and compare the first 10 Mars photos made by 'Curiosity' on 1000" +
            " sol and on Earth date equal to 1000 Martian sol.");
    MarsPhotosRestApiHelper helper = new MarsPhotosRestApiHelper();
    List<MarsRoverPhoto> earthPhotos = helper.retrieveMarsPhotos(ZERO, TEN, EMPTY_INT, EARTH_DATE_VALUE, EMPTY_STR, EMPTY_INT);
    List<MarsRoverPhoto> solPhotos = helper.retrieveMarsPhotos(ZERO, TEN, THOUSAND, EMPTY_STR, EMPTY_STR, EMPTY_INT);

    StepLogger.verification("Verify API response.");
    verifyValue(helper.testContext().getResponse().getStatusCode(), STATUS_CODE_OK, RESPONSE_CODE_MESSAGE);
    verifyValue(earthPhotos, solPhotos, PHOTOS_BY_CURIOSITY_MESSAGE);
  }

  @Feature("Validate that the amounts of pictures that each 'Curiosity' camera took on 1000 Mars sol is not greater than 10 times" +
          " the amount taken by other cameras on the same date.")
  @Test
  public void retrieveTotalPhotosBySol() {
    StepLogger.setCaseId(1004, null);
    StepLogger.step("Validate that the amounts of pictures that each 'Curiosity' camera took on 1000 Mars sol is not greater" +
            " than 10 times the amount taken by other cameras on the same date.");
    MarsPhotosRestApiHelper helper = new MarsPhotosRestApiHelper();
    PhotoManifest photoManifest = helper.retrievePhotoManifest(THOUSAND.getAsInt());

    StepLogger.verification("Verify API response.");
    verifyValue(helper.testContext().getResponse().getStatusCode(), STATUS_CODE_OK, RESPONSE_CODE_MESSAGE);
    Consumer<String> consumer = camera -> {
      int cameraPhotos = helper.retrieveMarsPhotos(EMPTY_INT, EMPTY_INT, OptionalInt.of(photoManifest.getSol()),
              Optional.ofNullable(photoManifest.getEarthDate()), Optional.of(camera), EMPTY_INT).size();
      int otherCameraPhotos = (photoManifest.getTotalPhotos() - cameraPhotos) * 10;
      verifyValue(helper.testContext().getResponse().getStatusCode(), STATUS_CODE_OK, RESPONSE_CODE_MESSAGE);
      String message = "The Amount of pictures taken by 'Curiosity' camera: " + camera + " on " + photoManifest.getSol()
              + " Mars sol should not be greater than 10 times the amount taken by other cameras on date " + photoManifest.getEarthDate();
      verifyValueGreaterThan(otherCameraPhotos, cameraPhotos, message);
    };
    photoManifest.getCameras().forEach(consumer);
  }
}
