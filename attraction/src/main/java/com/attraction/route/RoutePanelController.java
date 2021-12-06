package com.attraction.route;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoutePanelController {

  @GetMapping("/route")
  @PreAuthorize("hasRole('USER') or hasRole('GUIDE') or hasRole('ADMIN')")
  public String loadPanel() {
    loadRoutesList();
    return "route_panel";
  }

  private String loadRoutesList(){
    //TODO Implementation
    return "";
  }
}
