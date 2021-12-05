package com.attraction.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginPanelController {

  @RequestMapping("/login")
  public String loadPanel() {
    return "login";
  }
}
