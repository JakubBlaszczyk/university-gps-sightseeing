package com.attraction.ranking;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RankingPanelController {

  @RequestMapping("/ranking")
  public String loadPanel() {
    return "ranking";
  }
}
