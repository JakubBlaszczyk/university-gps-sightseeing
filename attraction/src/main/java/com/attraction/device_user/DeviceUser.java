package com.attraction.device_user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("devices_users")
public class DeviceUser {

  @Id
  Key id;

  Boolean isActive;
}

class Key {
  @DBRef
  String mac;
  Integer id;
}
