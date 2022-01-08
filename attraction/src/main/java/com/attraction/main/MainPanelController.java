package com.attraction.main;

import com.attraction.monument.Monument;
import com.attraction.monument.MonumentService;
import com.attraction.route.Route;
import com.attraction.route.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

@Controller
public class MainPanelController {

  @Autowired
  MonumentService monumentService;

  @Autowired
  RouteService routeService;

  @GetMapping("")
  public String loadPanel(Model model) {
    Random rand = new Random();
    Monument monument = monumentService.getMonument(rand.nextInt(monumentService.getMonuments().size()) + 1);
    model.addAttribute("monument", monument);
    Route route = routeService.getRoute(rand.nextInt(routeService.getAllRoutes().size()) + 1);
    model.addAttribute("route", route);
    return "main_panel";
  }
}
