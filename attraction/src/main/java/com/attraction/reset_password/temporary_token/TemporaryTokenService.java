package com.attraction.reset_password.temporary_token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemporaryTokenService {

  @Autowired
  TemporaryTokenRepository tokenRepository;

  public void save(TemporaryToken token) {
    tokenRepository.save(token);
  }
}
