package com.attraction.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPanelController {

  @RequestMapping("")
  public String main() {
    return "main_panel";
  }
}
