package com.attraction.reset_password.temporary_token;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

@Document("password_tokens")
@Value
public class TemporaryToken {

  @Id
  Integer token;
  Integer userId; // foreign attribute
}
