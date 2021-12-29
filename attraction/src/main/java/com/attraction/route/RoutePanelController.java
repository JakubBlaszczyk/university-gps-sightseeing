package com.attraction.route;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RoutePanelController {

  @Autowired
  private RouteService routeService;

  @GetMapping("/route")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public String loadPanel(){
    return "route_panel";
  }

  @GetMapping("/routes")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public List<Route> loadRoutesList() {
    return routeService.getAllRoutes();
  }

  @GetMapping("/route/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public Route loadRoute(@PathVariable Integer id) {
    return routeService.getRoute(id);
  }

}
