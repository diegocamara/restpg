package com.rpg.account.encoder;

public interface PasswordEncoder {
  String encode(String plainText);
}
