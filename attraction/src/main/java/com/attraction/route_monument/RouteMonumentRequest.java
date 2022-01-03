package com.attraction.route_monument;

import lombok.Value;

@Value
public class RouteMonumentRequest {
  Integer monumentId;
  Integer routeId;

  Integer position;
}
