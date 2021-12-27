package com.attraction.user;

import com.attraction.role.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

@Document("users")
@Value
public class UserObject implements Comparable<UserObject> {

  @Id
  Integer id;

  String username;
  String password;
  String email;
  String avatar;
  Integer points;
  String preference;
  Role role;

  @Override
  public int compareTo(UserObject temp) {
    return this.points - temp.getPoints();
  }
}
