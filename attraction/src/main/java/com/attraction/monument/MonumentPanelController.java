package com.attraction.monument;

import java.util.ArrayList;
import java.util.List;

import com.attraction.comment.Comment;
import com.attraction.comment.CommentService;
import com.attraction.security.MessageResponse;
import com.attraction.security.jwt.JwtUtils;
import com.attraction.user.User;
import com.attraction.user.UserLocationRequest;
import com.attraction.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MonumentPanelController {

  private static final String MONUMENT = "monument";

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
    model.addAttribute(MONUMENT, monumentService.getMonument(id));
    User user = userService.findByUsername(jwtUtils.getUserNameFromJwtToken(cookie.substring(cookie.indexOf('=') + 1)));
    model.addAttribute("activeUser", user);
    try {
      List<Comment> commentList = commentService.getMonumentComments(id);
      model.addAttribute("comments", commentList);
      List<User> userList = new ArrayList<>();
      for (Comment comment : commentList) {
        userList.add(userService.findById(comment.getUserId()));
      }
      model.addAttribute("users", userList);
    } catch (NullPointerException e) {
      return MONUMENT;
    }

    return MONUMENT;
  }

  @GetMapping("/monuments/city/{city}")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public @ResponseBody List<Monument> loadMonumentsByCity(@PathVariable String city) {
    return monumentService.getMonumentByCity(city);
  }

  @PostMapping("/locate")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public @ResponseBody ResponseEntity<MessageResponse> compareLocation(@Valid UserLocationRequest userLocationRequest) {
    Monument monument = monumentService.getMonument(userLocationRequest.getMonumentId());

    double disLatitude = Math.toRadians(Double.parseDouble(userLocationRequest.getLatitude()) - Double.parseDouble(monument.getLatitude()));
    double disLongitude = Math.toRadians(Double.parseDouble(userLocationRequest.getLongitude()) - Double.parseDouble(monument.getLongitude()));
    double sinDisLatitude = Math.sin(disLatitude / 2);
    double sinDisLongitude = Math.sin(disLongitude / 2);
    double a = Math.pow(sinDisLatitude, 2) + Math.pow(sinDisLongitude, 2) * Math.cos(Math.toRadians(Double.parseDouble(monument.getLatitude())) * Math.cos(Math.toRadians(Double.valueOf(userLocationRequest.getLatitude()))));
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    double distance = 6371 * c;

    if(distance <= 0.3) {
      User user = userService.findById(userLocationRequest.getUserId());
      userService.save(new User(userLocationRequest.getUserId(), user.getUsername(), user.getPassword(), user.getEmail(),
              user.getAvatar(), user.getPoints() + monument.getPriority(), user.getPreferredCity(), user.getPreferredMonument(),
              user.getRole()));
      return ResponseEntity.ok(new MessageResponse("Added points"));
    }
    else {
      return ResponseEntity.status(406).body(new MessageResponse("Too far from monument's location!"));
    }
  }
}
