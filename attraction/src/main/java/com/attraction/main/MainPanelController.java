package com.attraction.main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPanelController {

  @RequestMapping("/")
  public String loadPanel() {
    return "This is Main Panel";
  }
}
