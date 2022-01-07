package com.attraction.comment;

import java.util.List;

import com.attraction.monument.MonumentService;
import com.attraction.route.RouteService;
import com.attraction.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
  @Autowired
  CommentRepository commentRepository;

  @Autowired
  UserService userService;

  @Autowired
  MonumentService monumentService;

  @Autowired
  RouteService routeService;

  Integer findLastId() {
    List<Comment> temp = commentRepository.findAll();
    return !temp.isEmpty() ? temp.get(temp.size() - 1).getId() : 0;
  }

  List<Comment> getComments() {
    return commentRepository.findAll();
  }

  public List<Comment> getMonumentComments(Integer monumentId) {
    return commentRepository.findAll(Example.of(new Comment(null, null, monumentId, null, null, null, null, null)));
  }

  public List<Comment> getRouteComments(Integer routeId) {
    return commentRepository.findAll(Example.of(new Comment(null, null, null, routeId, null, null, null, null)));
  }

  public List<Comment> getUserComments(Integer userId) {
    return commentRepository.findAll(Example.of(new Comment(null, userId, null, null, null, null, null, null)));
  }

  public Boolean addComment(CommentRequest request) {
    if (!(request.getMonumentId() != null ^ request.getRouteId() != null)) {
      return false;
    }
    if (userService.getAllUsers().stream().noneMatch(n -> n.getId().equals(request.getUserId()))) {
      return false;
    }
    Boolean temp1 = monumentService.getMonuments().stream().noneMatch(n -> n.getId().equals(request.getMonumentId()));
    Boolean temp2 = routeService.getAllRoutes().stream().noneMatch(n -> n.getId().equals(request.getRouteId()));
    if (!(temp1
        ^ temp2)) {
      return false;
    }
    String time = java.time.LocalTime.now().toString();
    commentRepository
        .save(new Comment(findLastId() + 1, request.getUserId(), request.getMonumentId(), request.getRouteId(),
            request.getStars(), request.getContent(), java.time.LocalDate.now().toString(),
            time.substring(0, time.length() > 6 ? 8 : time.length())));
    return true;
  }
}
