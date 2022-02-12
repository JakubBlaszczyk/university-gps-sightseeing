package com.attraction.comment;

import java.util.List;

import com.attraction.security.MessageResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class CommentPanelController {

  private CommentService commentService;

  @GetMapping("/comments")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public @ResponseBody List<Comment> getComments() {
    return commentService.getComments();
  }

  @GetMapping("/comment/user/{userId}")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public @ResponseBody List<Comment> loadUserComments(@PathVariable Integer userId) {
    return commentService.getUserComments(userId);
  }

  @GetMapping("/comment/route/{routeId}")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public @ResponseBody List<Comment> loadRouteComments(@PathVariable Integer routeId) {
    return commentService.getRouteComments(routeId);
  }

  @GetMapping("/comment/monument/{monumentId}")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public @ResponseBody List<Comment> loadMonumentComments(@PathVariable Integer monumentId) {
    return commentService.getMonumentComments(monumentId);
  }

  @PostMapping("/comment")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public ResponseEntity<MessageResponse> addComment(CommentRequest request) {
    if (Boolean.TRUE.equals(commentService.addComment(request))) {
      return ResponseEntity.ok(new MessageResponse("Added comment"));
    }
    return ResponseEntity.badRequest().body(new MessageResponse("Failed to add comment"));
  }
}
