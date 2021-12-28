package com.attraction.route;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

  @Autowired
  RouteRepository routeRepository;

  public List<Route> getAllRoutes() {
    return routeRepository.findAll();
  }

  public Route getRoute(Integer id) {
    Optional<Route> result = routeRepository.findById(id);
    return result.isPresent() ? result.get() : null;
  }

}