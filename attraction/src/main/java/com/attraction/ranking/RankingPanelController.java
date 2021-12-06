package com.attraction.ranking;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RankingPanelController {

  @GetMapping("/ranking")
  @PreAuthorize("hasRole('USER') or hasRole('GUIDE') or hasRole('ADMIN')")
  public String loadPanel() {
    loadUsersList();
    return "ranking";
  }

  private String loadUsersList(){
    //TODO Loading users usernames with points
    return "";
  }
}
