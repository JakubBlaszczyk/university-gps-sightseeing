package com.attraction.report;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

@Document("reports")
@Value
public class Report {

  @Id
  Integer id;

  @DBRef
  String mac;
  @DBRef
  Integer userId;
  String content;
}
