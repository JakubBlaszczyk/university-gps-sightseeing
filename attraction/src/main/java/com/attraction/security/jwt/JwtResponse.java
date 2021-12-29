package com.attraction.security.jwt;

import lombok.Value;

@Value
public class JwtResponse {

  String token;
  String type = "Bearer";
  Integer id;
  String username;
  String email;
  String role;
}
