package com.attraction.route_monument;

import java.util.List;

import com.attraction.monument.Monument;
import com.attraction.security.MessageResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class RouteMonumentPanelController {

  private RouteMonumentService routeMonumentService;

  @PostMapping("/route/connect")
  @PreAuthorize("hasAnyAuthority('ADMIN')")
  public ResponseEntity<MessageResponse> connectRouteWithMonument(RouteMonumentRequest request) {
    if (Boolean.TRUE.equals(routeMonumentService.addRouteMonument(request))) {
      return ResponseEntity.ok(new MessageResponse("Connected successfully"));
    }
    return ResponseEntity.badRequest().body(new MessageResponse("Can't connect"));
  }

  @GetMapping("/route/{id}/monuments")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public @ResponseBody List<Monument> getMonumentsOnTheRoute(@PathVariable Integer id) {
    return routeMonumentService.getMonumentOnTheRoute(id);
  }

  @GetMapping("/routes/monuments")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public @ResponseBody List<RouteMonument> getMonumentsOnTheRoute() {
    return routeMonumentService.getMonumentsRoutes();
  }
}
