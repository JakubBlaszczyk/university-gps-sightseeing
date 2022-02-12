package com.attraction.suggested_monument;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

@Document("suggested_monuments")
@Value
public class SuggestedMonument {

  @Id
  Integer id;
  String name;
  String city;
  String type;
}
