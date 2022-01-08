package com.attraction.monument;

import java.util.ArrayList;
import java.util.List;

import com.attraction.comment.Comment;
import com.attraction.comment.CommentService;
import com.attraction.security.jwt.JwtUtils;
import com.attraction.user.User;
import com.attraction.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MonumentPanelController {

  @Autowired
  MonumentService monumentService;

  @Autowired
  CommentService commentService;

  @Autowired
  UserService userService;

  @Autowired
  JwtUtils jwtUtils;

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
  public String loadMonument(@RequestHeader String cookie, @PathVariable Integer id, Model model) {
    model.addAttribute("monument", monumentService.getMonument(id));
    User user = userService.findByUsername(jwtUtils.getUserNameFromJwtToken(cookie.substring(cookie.indexOf('=') + 1)));
    model.addAttribute("activeUser", user);
    try {
      List<Comment> commentList = commentService.getMonumentComments(id);
      model.addAttribute("comments", commentList);
      List<User> userList = new ArrayList<>();
      for (Comment comment : commentList){
        userList.add(userService.findById(comment.getUserId()));
      }
      model.addAttribute("users", userList);
    } catch(NullPointerException e){}

    return "monument";
  }

  @GetMapping("/monuments/city/{city}")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public @ResponseBody List<Monument> loadMonumentsByCity(@PathVariable String city) {
    return monumentService.getMonumentByCity(city);
  }
}
