package com.attraction.user;

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
  UserObjectService userService;

  @GetMapping("/user/{username}")
  @PreAuthorize("hasRole('USER') or hasRole('GUIDE') or hasRole('ADMIN')")
  public UserObject loadPanel(@PathVariable String username) {
    return userService.findByUsername(username);
  }

  @PostMapping("/user/{username}/change/password/{password}")
  @PreAuthorize("hasRole('USER') or hasRole('GUIDE') or hasRole('ADMIN')")
  public void changePassword(@PathVariable String username, @PathVariable String password) {
    UserObject userObject = userService.findByUsername(username);
    userService.save(new UserObject(userObject.getId(), userObject.getUsername(), password, userObject.getEmail(),
        userObject.getAvatar(), userObject.getPoints(),userObject.getPreference(), userObject.getRoles()));
  }
}
