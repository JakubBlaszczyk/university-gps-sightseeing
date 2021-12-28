package com.attraction.security;

import com.attraction.user.User;
import com.attraction.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  UserService userService;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      User userObject = userService.findByUsername(username);
      return CustomUserDetails.build(userObject);
    } catch (Exception exception) {
      throw new UsernameNotFoundException("User not found");
    }
  }
}
