package com.attraction.suggestedmonument;

import javax.validation.constraints.NotBlank;

import lombok.Value;

@Value
public class SuggestedMonumentRequest {

  @NotBlank
  String name;

  @NotBlank
  String city;

  @NotBlank
  String type;
}
