package com.attraction.monument;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("monuments")
public class Monument {

  @Id
  private Integer id;

  private String name;
  private Integer priority;
  private Double longitude;
  private Double latitude;
  private String description;
  private String type;
  private String photo;
  private String city;

  public Monument(Integer id, String name, Integer priority, Double longitude, Double latitude, String description,
      String type, String photo, String city) {
    super();
    this.id = id;
    this.name = name;
    this.priority = priority;
    this.longitude = longitude;
    this.latitude = latitude;
    this.description = description;
    this.type = type;
    this.photo = photo;
    this.city = city;
  }

  public String getName() {
    return this.name;
  }
}
