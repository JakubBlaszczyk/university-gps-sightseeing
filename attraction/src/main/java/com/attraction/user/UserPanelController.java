package com.attraction.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserPanelController {

  @Autowired
  private UserObjectRepository userRepo;

  @GetMapping("/user/{username}")
  @PreAuthorize("hasRole('USER') or hasRole('GUIDE') or hasRole('ADMIN')")
  public UserObject loadPanel(@PathVariable String username) {
    return userRepo.findByUsername(username);
  }
}
