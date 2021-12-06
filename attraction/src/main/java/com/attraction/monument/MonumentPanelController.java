package com.attraction.monument;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MonumentPanelController {

  @RequestMapping("/monument")
  public String loadPanel() {
    loadMonumentsList();
    return "monument_panel";
  }

  private String loadMonumentsList() {
    //TODO Implemetation
    return "";
  }
}
