package com.attraction.route_monument;

import java.util.List;

import com.attraction.monument.Monument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RouteMonumentPanelController {

  @Autowired
  RouteMonumentService routeMonumentService;

  @GetMapping("/route/{id}/monuments")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public @ResponseBody List<Monument> getMonumentsOnTheRoute(@PathVariable Integer id) {
    return routeMonumentService.getMonumentOnTheRoute(id);
  }
}
