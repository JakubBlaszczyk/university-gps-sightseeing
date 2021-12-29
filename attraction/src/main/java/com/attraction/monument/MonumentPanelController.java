package com.attraction.monument;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MonumentPanelController {

  @Autowired
  MonumentService monumentService;

  @GetMapping("/monument")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public String loadPanel() {
    return "monument_panel";
  }

  @GetMapping("/monuments")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public @ResponseBody List<Monument> loadMonuments() {
    return monumentService.getMonumets();
  }

  @GetMapping("/monuments/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public @ResponseBody Monument loadMonument(@PathVariable Integer id) {
    return monumentService.getMonument(id);
  }

  @GetMapping("/monuments/city/{city}")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public @ResponseBody List<Monument> loadMonumentsByCity(@PathVariable String city) {
    return monumentService.getMonumentByCity(city);
  }

  @PostMapping("/monument/{name}/{longitude}/{latitude}/{description}/{type}/{photo}/{city}")
  @PreAuthorize("hasAnyAuthority('GUIDE', 'ADMIN')")
  public @ResponseBody List<Monument> addMonument(@PathVariable String name, @PathVariable Double longitude,
      @PathVariable Double latitude, @PathVariable String description,
      @PathVariable String type, @PathVariable String photo, @PathVariable String city) {
    return monumentService.getMonumets();
  }
}
