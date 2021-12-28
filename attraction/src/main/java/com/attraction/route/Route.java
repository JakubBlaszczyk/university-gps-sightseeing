package com.attraction.route;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

@Document("routes")
@Value
public class Route {

  @Id
  Integer id;

  String name;
  Integer length;
  String description;
  String city;
  String mapLink;
}
