package com.deviget.model;

import lombok.Data;

import java.util.LinkedHashMap;

@Data
public class MarsRover {

  private Integer id;
  private String name;
  private String landingDate;
  private String launchDate;
  private String status;
}
