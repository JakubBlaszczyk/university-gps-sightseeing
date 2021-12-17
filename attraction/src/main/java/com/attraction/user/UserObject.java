package com.attraction.user;

import com.attraction.role.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

import java.util.Set;

@Document("users")
@Value
public class UserObject implements Comparable {

  @Id
  Integer id;

  String username;
  String password;
  String email;
  String avatar;
  Integer points;
  String preference;
  Set<Role> roles;

  @Override
  public int compareTo(Object o) {
    UserObject temp = (UserObject)o;
    return this.points - temp.getPoints();
  }
}
