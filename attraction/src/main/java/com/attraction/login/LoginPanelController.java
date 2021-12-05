package com.attraction.login;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginPanelController {

  @RequestMapping("/login")
  public String loadPanel() {
    return "This is Login Panel";
  }
}
