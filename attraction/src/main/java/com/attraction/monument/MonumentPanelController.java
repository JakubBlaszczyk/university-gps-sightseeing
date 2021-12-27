package com.attraction.monument;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MonumentPanelController {

  @GetMapping("/monument")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public String loadPanel() {
    loadMonumentsList();
    return "monument_panel";
  }

  private String loadMonumentsList() {
    //TODO Implemetation
    return "";
  }
}
