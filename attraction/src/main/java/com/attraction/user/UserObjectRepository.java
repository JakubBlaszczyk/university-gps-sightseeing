package com.attraction.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserObjectRepository extends MongoRepository<UserObject, String> {
  UserObject findByUsername(String username);
}
