package com.attraction.route;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoutePanelController {

  @Autowired
  private RouteService routeService;
  @GetMapping("/route")
  @PreAuthorize("hasRole('USER') or hasRole('GUIDE') or hasRole('ADMIN')")
  public List<Route> loadPanel() {
    return routeService.getAllRoutes();
  }

}
