package com.attraction.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPanelController {

  @Autowired
  private UserObjectRepository userRepo;

  @GetMapping("/user/{username}")
  public UserObject loadPanel(@PathVariable String username) {
    return userRepo.findByUsername(username);
  }
}
