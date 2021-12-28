package com.attraction.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserPanelController {

  @Autowired
  UserService userService;

  @GetMapping("/user/{username}")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public User loadPanel(@PathVariable String username) {
    return userService.findByUsername(username);
  }

  @GetMapping("/users")
  @PreAuthorize("hasAnyAuthority('ADMIN')")
  public List<User> getUsers() {
    return userService.getAllUsers();
  }

  @PostMapping("/user/{username}/change/password/{password}")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public void changePassword(@PathVariable String username, @PathVariable String password) {
    User userObject = userService.findByUsername(username);
    userService.save(new User(userObject.getId(), userObject.getUsername(), password, userObject.getEmail(),
        userObject.getAvatar(), userObject.getPoints(),userObject.getPreference(), userObject.getRole()));
  }
}
