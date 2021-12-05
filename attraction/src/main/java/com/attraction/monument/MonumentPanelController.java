package com.attraction.monument;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonumentPanelController {

  @RequestMapping("/monument")
  public String loadPanel() {
    return "This is Monument Panel";
  }
}
