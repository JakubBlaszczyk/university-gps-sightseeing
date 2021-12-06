package com.attraction.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserObjectRepository extends MongoRepository<UserObject, String> {

  @Query("{username: '?0'}")
  UserObject findByUsername(String username);
}
