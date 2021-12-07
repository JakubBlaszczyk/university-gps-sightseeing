package com.attraction.route;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface RouteRepository extends MongoRepository<Route, Integer> {
}
