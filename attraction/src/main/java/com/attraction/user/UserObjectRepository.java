package com.attraction.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserObjectRepository extends MongoRepository<UserObject, String> {

  UserObject findByUsername(String username);

  @Query("select max(id) from users")
  Integer findMaxId();

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
