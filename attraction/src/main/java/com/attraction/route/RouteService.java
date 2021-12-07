package com.attraction.route;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

  @Autowired
  RouteRepository routeRepository;

  public List<Route> getAllRoutes() {
    return routeRepository.findAll();
  }
  
}
