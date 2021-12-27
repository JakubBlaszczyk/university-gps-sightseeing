package com.attraction.ranking;

import com.attraction.role.Role;

import lombok.Value;

@Value
public class RankingEntity {

  String username;
  Integer points;
  String avatar;
  Role role;
}
