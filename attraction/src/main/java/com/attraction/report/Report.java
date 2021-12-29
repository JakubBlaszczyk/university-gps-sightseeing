package com.attraction.report;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

@Document("reports")
@Value
public class Report {

  @Id
  Integer id;

  Integer userId; // foreign attribute
  String content;
}
