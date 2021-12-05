package com.attraction.monument;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MonumentRepository extends MongoRepository<Monument, Integer> {

  @Query("{name: '?0'}")
  Monument findMonumentByName(String name);

  @Query("{city: '?0'}")
  Monument findMonumentsInCity(String city);

  public long count();
}
