package com.attraction.signup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Value;

@Value
public class SignupRequest {

  @NotBlank
  @Size(max = 50)
  @Email
  String email;

  @NotBlank
  @Size(min = 3, max = 20)
  String username;

  @NotBlank
  @Size(min = 8, max = 40)
  String password;

  String role;
}
