package com.attraction.user;

import com.attraction.role.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

import java.util.HashSet;
import java.util.Set;

@Document("users")
public class UserObject {

  @Id
  String id;

  String username;
  String password;
  String email;
  String avatar;
  Integer points;
  String preference;
  Set<Role> roles = new HashSet<>();

  public UserObject(String username, String email, String password, Set<Role> roles) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  public String getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public String getAvatar() {
    return avatar;
  }

  public Integer getPoints() {
    return points;
  }

  public String getPreference() {
    return preference;
  }

  public Set<Role> getRoles() {
    return roles;
  }

}
