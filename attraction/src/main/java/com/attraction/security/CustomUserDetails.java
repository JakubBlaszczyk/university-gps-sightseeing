package com.attraction.security;

import com.attraction.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Value;

import java.util.Collection;
import java.util.List;

@Value
public class CustomUserDetails implements UserDetails {

  Integer id;
  String username;
  String email;

  @JsonIgnore
  String password;

  GrantedAuthority authorities;

  public static CustomUserDetails build(User userObject) {
    GrantedAuthority authorities = new SimpleGrantedAuthority(userObject.getRole().toString());

    return new CustomUserDetails(
            userObject.getId(),
            userObject.getUsername(),
            userObject.getEmail(),
            userObject.getPassword(),
            authorities
    );
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(this.authorities);
  }

  public GrantedAuthority getAuthority() {
    return this.authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
