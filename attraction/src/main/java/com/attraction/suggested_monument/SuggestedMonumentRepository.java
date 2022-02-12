package com.attraction.suggested_monument;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SuggestedMonumentRepository extends MongoRepository<SuggestedMonument, Integer> {

}
