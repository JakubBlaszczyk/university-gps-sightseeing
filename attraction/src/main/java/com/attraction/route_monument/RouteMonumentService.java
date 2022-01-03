package com.attraction.route_monument;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.attraction.monument.Monument;
import com.attraction.monument.MonumentService;
import com.attraction.route.RouteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteMonumentService {

  @Autowired
  RouteService routeService;

  @Autowired
  MonumentService monumentService;

  @Autowired
  RouteMonumentRepository routeMonumentRepository;

  Boolean addRouteMonument(RouteMonumentRequest request) {
    if (routeService.getAllRoutes().stream().anyMatch(n -> n.getId().equals(request.getRouteId()))) {
      return false;
    }
    if (monumentService.getMonuments().stream().anyMatch(n -> n.getId().equals(request.getMonumentId()))) {
      return false;
    }
    routeMonumentRepository
        .save(new RouteMonument(new Key(request.getRouteId(), request.getMonumentId()), request.getPosition()));
    return true;
  }

  public List<Monument> getMonumentOnTheRoute(Integer routeId) {
    List<RouteMonument> routeMonuments = routeMonumentRepository.findAll().stream()
        .filter(n -> n.getId().getRouteId().equals(routeId)).collect(Collectors.toList());
    List<Monument> result = new ArrayList<>(routeMonuments.size());
    for (RouteMonument routeMonument : routeMonuments) {
      result.add(monumentService.getMonument(routeMonument.getId().getMonumentId()));
    }
    return result;
  }
}
