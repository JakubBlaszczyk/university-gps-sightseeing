package com.attraction.ranking;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class RankingPanelController {

  private RankingService rankingService;

  @GetMapping("/ranking")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public String loadPanel(Model model) {
    model.addAttribute("users", rankingService.loadSortedList());
    return "ranking";
  }

  @GetMapping("/rankingList")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public @ResponseBody List<RankingEntity> loadUsersList() {
    return rankingService.loadSortedList();
  }
}
