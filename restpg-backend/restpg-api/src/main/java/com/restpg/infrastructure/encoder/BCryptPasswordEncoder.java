package com.restpg.infrastructure.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptPasswordEncoder
    implements com.restpg.application.context.account.encoder.PasswordEncoder {

  private final PasswordEncoder passwordEncoder;

  public BCryptPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public String encode(String plainText) {
    return passwordEncoder.encode(plainText);
  }

  @Override
  public boolean isValid(String plainPassword, String hashedPassword) {
    return passwordEncoder.matches(plainPassword, hashedPassword);
  }
}
