package com.attraction.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  UserRepository userRepo;

  public Integer findMaxId() {
    return this.userRepo.findAll().size();
  }

  public Boolean existsByEmail(String email) {
    return userRepo.exists(Example.of(new User(null, null, null, email, null, null, null, null)));
  }

  public Boolean existsByUsername(String username) {
    return userRepo.exists(Example.of(new User(null, username, null, null, null, null, null, null)));
  }

  public User findByUsername(String username) {
    Optional<User> result = userRepo.findOne(Example.of(new User(null, username, null, null, null, null, null, null)));
    if (!result.isPresent()) {
      throw new UsernameNotFoundException("No such username");
    }
    return  result.get();
  }

  public List<User> getAllUsers() {
    return userRepo.findAll();
  }

  public void save(User object) {
    userRepo.save(object);
  }
}
