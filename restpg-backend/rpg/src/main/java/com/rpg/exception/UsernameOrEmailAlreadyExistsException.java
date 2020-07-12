package com.rpg.exception;

public class UsernameOrEmailAlreadyExistsException extends RPGException {
  public UsernameOrEmailAlreadyExistsException() {
    super("username or email already exists");
  }
}
