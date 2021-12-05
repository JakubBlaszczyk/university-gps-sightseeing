package com.attraction.ranking;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RankingPanelController {

  @RequestMapping("/ranking")
  public String loadPanel() {
    return "This is Ranking Panel";
  }
}
