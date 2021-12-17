package com.attraction.ranking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RankingPanelController {

  @Autowired
  RankingService rankingService;

  @GetMapping("/ranking")
  @PreAuthorize("hasRole('USER') or hasRole('GUIDE') or hasRole('ADMIN')")
  public List<RankingEntity> loadUsersList() {
    return rankingService.loadSortedList();
  }
}
