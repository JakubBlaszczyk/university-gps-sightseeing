package com.attraction.security;

import com.attraction.user.UserObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Value;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Value
public class CustomUserDetails implements UserDetails {

  String id;
  String username;
  String email;

  @JsonIgnore
  String password;

  GrantedAuthority authorities;

  public CustomUserDetails(String id, String username, String email, String password, GrantedAuthority authorities) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  public static CustomUserDetails build(UserObject userObject) {
    GrantedAuthority authorities = new SimpleGrantedAuthority(userObject.getRole().toString());

    return new CustomUserDetails(
            userObject.getId().toString(),
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

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    CustomUserDetails user = (CustomUserDetails) o;
    return Objects.equals(id, user.id);
  }
}
