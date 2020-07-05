package com.restpg.infrastructure.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptPasswordEncoder implements com.rpg.account.encoder.PasswordEncoder {

  private final PasswordEncoder passwordEncoder;

  public BCryptPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public String encode(String plainText) {
    return passwordEncoder.encode(plainText);
  }
}
