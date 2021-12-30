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
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public String loadPanel() {
    return "ranking";
  }

  @GetMapping("/rankingList")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public List<RankingEntity> loadUsersList() {
    return rankingService.loadSortedList();
  }
}
