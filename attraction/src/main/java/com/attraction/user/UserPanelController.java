package com.attraction.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPanelController {

  @RequestMapping("/user")
  public String loadPanel() {
    return "This is User Panel";
  }
}
