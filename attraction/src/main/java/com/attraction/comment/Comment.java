package com.attraction.comment;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Value;

@Value
public class Comment {
  @Id
  Integer id;

  @DBRef
  Integer userId;
  @DBRef
  Integer monumentId;
  @DBRef
  Integer routeId;

  Integer stars;
  String content;
}
