package com.attraction.ranking;

import java.util.List;
import java.util.stream.Collectors;

import com.attraction.user.UserService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RankingService {

  private UserService userService;

  List<RankingEntity> loadSortedList() {
    return userService.getAllUsers().stream().sorted((o1, o2) -> -o1.compareTo(o2))
        .map(x -> new RankingEntity(x.getUsername(), x.getPoints(), x.getAvatar(), x.getRole())).collect(Collectors.toList());
  }
}
