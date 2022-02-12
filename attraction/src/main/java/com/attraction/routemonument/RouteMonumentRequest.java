package com.attraction.routemonument;

import lombok.Value;

@Value
public class RouteMonumentRequest {
  Integer monumentId;
  Integer routeId;

  Integer position;
}
