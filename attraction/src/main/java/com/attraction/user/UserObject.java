package com.attraction.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

@Document("users")
@Value
public class UserObject {

  @Id
  Integer id;

  String username;
  String password;
  String email;
  String avatar;
  Integer points;
  String preference;
  String role;
}
