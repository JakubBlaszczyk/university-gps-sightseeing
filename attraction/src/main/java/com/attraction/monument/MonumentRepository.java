package com.attraction.monument;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MonumentRepository extends MongoRepository<Monument, Integer> {

}
