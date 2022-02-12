package com.attraction.routemonument;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

@Document("routes_monuments")
@Value
public class RouteMonument {

  @Id
  Key id;

  @Value
  static class Key {
    Integer routeId;
    Integer monumentId;
  }

  Integer position;

}
