package com.attraction.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserObjectService {

  @Autowired
  UserObjectRepository userRepo;

  public Integer findMaxId() {
    List<UserObject> list = this.userRepo.findAll();
    return list.get(list.size() - 1).getId();
  }

  public Boolean existsByEmail(String email) {
    return userRepo.existsByEmail(email);
  }

  public Boolean existsByUsername(String name) {
    return userRepo.existsByUsername(name);
  }

  public UserObject findByUsername(String name) {
    return userRepo.findByUsername(name);
  }

  public List<UserObject> getAllUsers() {
    return userRepo.findAll();
  }

  public void save(UserObject object) {
    userRepo.save(object);
  }
}
