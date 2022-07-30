package com.deviget.core.api;

import com.deviget.model.MarsRoverPhoto;
import com.deviget.model.PhotoManifest;
import io.restassured.response.Response;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static com.deviget.core.CoreConstants.*;

public class MarsPhotosRestApiHelper extends BaseRestApiHelper {

  public List<MarsRoverPhoto> retrieveMarsPhotos(
          OptionalInt fromIndex, OptionalInt toIndex, OptionalInt sol, Optional<String> earthDate, Optional<String> camera, OptionalInt page) {
    var pathParams = getPathParams(sol, earthDate, camera, page);
    Response response = RestAssuredExtension.getInstance().get(ROVERS_CURIOSITY, pathParams);
    testContext().setResponse(response);
    List<MarsRoverPhoto> photos = response.getBody().jsonPath().getList(PHOTOS, MarsRoverPhoto.class);
    if (fromIndex.isPresent() && toIndex.isPresent()) {
      return photos.subList(fromIndex.getAsInt(), toIndex.getAsInt());
    }
    return photos;
  }

  public PhotoManifest retrievePhotoManifest(int sol) {
    var pathParams = getPathParams(EMPTY_INT, EMPTY_STR, EMPTY_STR, EMPTY_INT);
    Response response = RestAssuredExtension.getInstance().get(MANIFESTS_CURIOSITY, pathParams);
    testContext().setResponse(response);
    PhotoManifest photoManifest = response.jsonPath().getObject("photo_manifest.photos.find { it.sol==" + sol + " }", PhotoManifest.class);
    return photoManifest;
  }

  @NotNull
  private Map<String, String> getPathParams(OptionalInt sol, Optional<String> earthDate, Optional<String> camera, OptionalInt page) {
    var pathParams = new HashMap<String, String>();
    sol.ifPresent(el -> pathParams.put(SOL, String.valueOf(el)));
    earthDate.ifPresent(el -> pathParams.put(EARTH_DATE, el));
    camera.ifPresent(el -> pathParams.put(CAMERA, el));
    page.ifPresent(el -> pathParams.put(PAGE, String.valueOf(el)));
    pathParams.put(API_KEY_NAME, API_KEY);
    return pathParams;
  }
}
