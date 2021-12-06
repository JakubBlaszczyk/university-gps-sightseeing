package com.attraction.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPanelController {

  @GetMapping("")
  public String loadPanel() {
    return "main_panel";
  }
}
