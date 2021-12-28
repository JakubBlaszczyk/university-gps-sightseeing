package com.attraction.comment;

import java.util.List;

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

  List<Comment> getMonumentComments(Integer monumentId) {
    return commentRepository.findAll(Example.of(new Comment(null, null, monumentId, null, null, null)));
  }

  List<Comment> getRouteComments(Integer routeId) {
    return commentRepository.findAll(Example.of(new Comment(null, null, null, routeId, null, null)));
  }

  List<Comment> getUserComments(Integer userId) {
    return commentRepository.findAll(Example.of(new Comment(null, userId, null, null, null, null)));
  }
}
