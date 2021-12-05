package com.attraction.monument;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MonumentPanelController {

  @RequestMapping("/monument")
  public String loadPanel() {
    return "monument_panel";
  }
}
