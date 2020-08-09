package com.restpg.domain.account.exception;

import com.rpg.exception.RPGException;

public class IncorrectEmailOrPasswordException extends RPGException {
  public IncorrectEmailOrPasswordException() {
    super("incorrect email or password");
  }
}
