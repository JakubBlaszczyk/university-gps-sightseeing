package com.attraction.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Value;

@Value
public class UserRequest {

  @NotBlank
  String jwtToken;

  @Email
  @Size(max = 50)
  String email;

  @Size(min = 8, max = 40)
  String password;

  @Size(min = 3, max = 20)
  String username;
}