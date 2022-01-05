package com.attraction.reset_password;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Value;

@Value
public class ResetPasswordRequest {

  @Email
  @Size(max = 50)
  @NotBlank
  String email;
}
