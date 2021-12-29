package com.attraction.user;

import com.attraction.role.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

@Document("users")
@Value
public class User implements Comparable<User> {

  @Id
  Integer id;

  String username;
  String password;
  String email;
  String avatar;
  Integer points;
  String preferredCity;
  String preferredMonument;
  Role role;

  @Override
  public int compareTo(User temp) {
    return this.points - temp.getPoints();
  }
}
