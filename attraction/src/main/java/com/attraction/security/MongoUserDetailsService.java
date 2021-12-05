package com.attraction.security;

import com.attraction.user.UserObject;
import com.attraction.user.UserObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MongoUserDetailsService implements UserDetailsService {

  @Autowired
  private UserObjectRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserObject userObject = repository.findByUsername(username);

    if(userObject == null) {
      throw new UsernameNotFoundException("User not found");
    }

    //Can be changed to implement multiple roles
    List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));

    return new User(userObject.getUsername(), userObject.getPassword(), authorities);
  }
}
