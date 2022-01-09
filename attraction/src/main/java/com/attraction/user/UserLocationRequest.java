package com.attraction.user;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class UserLocationRequest {

  @NotBlank
  Integer monumentId;

  @NotBlank
  Integer userId;

  @NotBlank
  String longitude;

  @NotBlank
  String latitude;
}
