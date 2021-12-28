package com.attraction.device;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

@Document("devices")
@Value
public class Device {
  
  @Id
  @Size(min = 12, max = 12)
  String mac;

  Double longitude;
  Double latitude;
}
