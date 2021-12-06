package com.attraction.history;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

@Document("history")
@Value
public class History {

  @Id
  Key id;

}

@Value
class Key implements Serializable {
  Date date;
  Time time;

  @DBRef
  Integer userId;
  Integer monumentId;
}