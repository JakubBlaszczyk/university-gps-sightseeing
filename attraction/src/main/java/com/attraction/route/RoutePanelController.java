package com.attraction.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoutePanelController {

  @RequestMapping("/route")
  public String loadPanel() {
    loadRoutesList();
    return "route_panel";
  }

  private String loadRoutesList(){
    //TODO Implementation
    return "";
  }
}
