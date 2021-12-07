package com.attraction.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserObjectRepository extends MongoRepository<UserObject, String> {

  UserObject findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
