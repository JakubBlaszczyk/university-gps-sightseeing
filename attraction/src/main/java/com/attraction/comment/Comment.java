package com.attraction.comment;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("comments")
@Value
public class Comment {
  @Id
  Integer id;

  Integer userId;
  Integer monumentId;
  Integer routeId;

  Integer stars;
  String content;
  String date;
  String time;
}
