package com.attraction.route;

import java.util.ArrayList;
import java.util.List;

import com.attraction.comment.Comment;
import com.attraction.comment.CommentService;
import com.attraction.route_monument.RouteMonumentService;
import com.attraction.security.jwt.JwtUtils;
import com.attraction.user.User;
import com.attraction.user.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class RoutePanelController {

  private static final String ROUTE = "route";

  private RouteService routeService;
  private RouteMonumentService routeMonumentService;
  private CommentService commentService;
  private UserService userService;
  private JwtUtils jwtUtils;

  @GetMapping("/route")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public String loadPanel(Model model) {
    model.addAttribute("routes", routeService.getAllRoutes());
    return "route_panel";
  }

  @GetMapping("/routes")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public List<Route> loadRoutesList() {
    return routeService.getAllRoutes();
  }

  @GetMapping("/route/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public String loadRoute(@RequestHeader String cookie, @PathVariable Integer id, Model model) {
    model.addAttribute(ROUTE, routeService.getRoute(id));
    model.addAttribute("monuments", routeMonumentService.getMonumentOnTheRoute(id));
    User user = userService.findByUsername(jwtUtils.getUserNameFromJwtToken(cookie.substring(cookie.indexOf('=') + 1)));
    model.addAttribute("activeUser", user);
    try {
      List<Comment> commentList = commentService.getRouteComments(id);
      model.addAttribute("comments", commentList);
      List<User> userList = new ArrayList<>();
      for (Comment comment : commentList) {
        userList.add(userService.findById(comment.getUserId()));
      }
      model.addAttribute("users", userList);
    } catch (NullPointerException e) {
      return ROUTE;
    }
    return ROUTE;
  }

}
