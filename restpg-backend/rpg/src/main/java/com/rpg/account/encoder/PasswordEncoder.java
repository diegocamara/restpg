package com.rpg.account.encoder;

public interface PasswordEncoder {
  String encode(String plainText);

  boolean isValid(String plainPassword, String hashedPassword);
}
