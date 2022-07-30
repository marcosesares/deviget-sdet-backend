
package com.deviget.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MarsRoverPhoto {

    @Expose
    private Long id;
    @Expose
    private Long sol;
    @Expose
    private MarsRoverCamera camera;
    @SerializedName("img_src")
    private String imgSrc;
    @SerializedName("earth_date")
    private String earthDate;
    @Expose
    private MarsRover rover;
}
