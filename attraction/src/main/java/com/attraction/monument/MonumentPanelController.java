package com.attraction.monument;

import java.util.List;

import com.attraction.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MonumentPanelController {

  @Autowired
  MonumentService monumentService;

  @Autowired
  CommentService commentService;

  @GetMapping("/monument")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public String loadPanel(Model model) {
    model.addAttribute("monuments", monumentService.getMonuments());
    return "monument_panel";
  }

  @GetMapping("/monuments")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public @ResponseBody List<Monument> loadMonuments() {
    return monumentService.getMonuments();
  }

  @GetMapping("/monuments/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public String loadMonument(@PathVariable Integer id, Model model) {
    model.addAttribute("monument", monumentService.getMonument(id));

    model.addAttribute("comments", commentService.getMonumentComments(id));

    return "monument";
  }

  @GetMapping("/monuments/city/{city}")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public @ResponseBody List<Monument> loadMonumentsByCity(@PathVariable String city) {
    return monumentService.getMonumentByCity(city);
  }
}
