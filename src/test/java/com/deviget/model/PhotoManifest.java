package com.deviget.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class PhotoManifest {

  @Expose
  private Integer sol;
  @SerializedName("total_photos")
  private Integer totalPhotos;
  @SerializedName("earth_date")
  private String earthDate;
  @Expose
  private List<String> cameras;
}
