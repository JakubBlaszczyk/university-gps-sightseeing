package com.attraction.reset_password.temporary_token;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemporaryTokenRepository extends MongoRepository<TemporaryToken, String> {

}
