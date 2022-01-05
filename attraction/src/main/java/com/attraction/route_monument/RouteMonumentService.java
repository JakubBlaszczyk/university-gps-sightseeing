package com.attraction.route_monument;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.attraction.monument.Monument;
import com.attraction.monument.MonumentService;
import com.attraction.route.RouteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attraction.route_monument.RouteMonument.Key;
@Service
public class RouteMonumentService {

  @Autowired
  RouteService routeService;

  @Autowired
  MonumentService monumentService;

  @Autowired
  RouteMonumentRepository routeMonumentRepository;

  Boolean addRouteMonument(RouteMonumentRequest request) {
    if (routeService.getAllRoutes().stream().noneMatch(n -> n.getId().equals(request.getRouteId()))) {
      return false;
    }
    if (monumentService.getMonuments().stream().noneMatch(n -> n.getId().equals(request.getMonumentId()))) {
      return false;
    }
    routeMonumentRepository
        .save(new RouteMonument(new Key(request.getRouteId(), request.getMonumentId()), request.getPosition()));
    return true;
  }

  public List<Monument> getMonumentOnTheRoute(Integer routeId) {
    List<RouteMonument> routeMonuments = routeMonumentRepository.findAll().stream()
        .filter(n -> n.getId().getRouteId().equals(routeId)).collect(Collectors.toList());
    routeMonuments.sort(Comparator.comparing(RouteMonument::getPosition));
    List<Monument> result = new ArrayList<>(routeMonuments.size());
    for (RouteMonument routeMonument : routeMonuments) {
      result.add(monumentService.getMonument(routeMonument.getId().getMonumentId()));
    }
    return result;
  }

  public List<RouteMonument> getMonumentsRoutes() {
    return routeMonumentRepository.findAll();
  }
}
