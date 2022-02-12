package com.attraction.route;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RouteService {

  private RouteRepository routeRepository;

  public List<Route> getAllRoutes() {
    return routeRepository.findAll();
  }

  public Route getRoute(Integer id) {
    Optional<Route> result = routeRepository.findById(id);
    return result.isPresent() ? result.get() : null;
  }

}