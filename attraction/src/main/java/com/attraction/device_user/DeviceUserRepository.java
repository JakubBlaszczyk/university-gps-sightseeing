package com.attraction.device_user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceUserRepository extends MongoRepository<DeviceUser, Key> {

}
