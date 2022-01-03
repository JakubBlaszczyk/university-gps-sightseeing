package com.attraction.comment;

import lombok.Value;

@Value
public class CommentRequest {
  
  Integer userId;
  Integer monumentId;
  Integer routeId;

  Integer stars;
  String content;
}
