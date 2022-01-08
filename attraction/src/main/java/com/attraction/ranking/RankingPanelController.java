package com.attraction.ranking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RankingPanelController {

  @Autowired
  RankingService rankingService;

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
