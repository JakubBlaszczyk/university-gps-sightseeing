package com.attraction.route_monument;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

@Document("routes_monuments")
@Value
public class RouteMonument {

  @Id
  Key id;

  Integer position;

}

@Value
class Key {
    Integer routeId;
    Integer monumentId;
  }