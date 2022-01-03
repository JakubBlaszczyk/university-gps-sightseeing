package com.attraction.comment;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
  @Autowired
  CommentRepository commentRepository;

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
}
