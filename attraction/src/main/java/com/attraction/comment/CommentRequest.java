package com.attraction.comment;

import javax.validation.constraints.NotNull;

import lombok.Value;

@Value
public class CommentRequest {

  @NotNull
  Integer userId;
  Integer monumentId;
  Integer routeId;

  @NotNull
  Integer stars;
  @NotNull
  String content;
}
