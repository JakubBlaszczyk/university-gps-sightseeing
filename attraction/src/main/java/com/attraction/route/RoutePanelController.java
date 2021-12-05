package com.attraction.route;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoutePanelController {

  @RequestMapping("/route")
  public String loadPanel() {
    return "This is Route Panel";
  }
}
