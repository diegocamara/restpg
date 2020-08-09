package com.restpg.domain.account.exception;

import com.rpg.exception.RPGException;

public class UsernameOrEmailAlreadyExistsException extends RPGException {
  public UsernameOrEmailAlreadyExistsException() {
    super("username or email already exists");
  }
}
