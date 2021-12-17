package com.attraction.ranking;

import java.util.Set;

import com.attraction.role.Role;

import lombok.Value;

@Value
public class RankingEntity {

  String username;
  Integer points;
  String avatar;
  Set<Role> role;
}
