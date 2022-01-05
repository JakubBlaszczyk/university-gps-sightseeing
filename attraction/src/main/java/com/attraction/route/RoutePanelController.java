package com.attraction.route;

import java.util.Comparator;
import java.util.List;

import com.attraction.route_monument.RouteMonumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RoutePanelController {

  @Autowired
  private RouteService routeService;

  @Autowired
  private RouteMonumentService routeMonumentService;

  @GetMapping("/route")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public String loadPanel(Model model){
    model.addAttribute("routes", routeService.getAllRoutes());
    return "route_panel";
  }

  @GetMapping("/routes")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public List<Route> loadRoutesList() {
    return routeService.getAllRoutes();
  }

  @GetMapping("/route/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public String loadRoute(@PathVariable Integer id, Model model) {
    model.addAttribute("route", routeService.getRoute(id));
    model.addAttribute("monuments", routeMonumentService.getMonumentOnTheRoute(id));
    return "route";
  }

}
