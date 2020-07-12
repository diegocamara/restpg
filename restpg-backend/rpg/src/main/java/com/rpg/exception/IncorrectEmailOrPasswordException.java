package com.rpg.exception;

public class IncorrectEmailOrPasswordException extends RPGException {
  public IncorrectEmailOrPasswordException() {
    super("incorrect email or password");
  }
}
