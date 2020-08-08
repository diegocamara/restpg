package com.restpg.application.context.account.model;

import com.rpg.validator.ModelValidator;

import java.util.UUID;

public class AccountCreator {

  private final ModelValidator modelValidator;

  public AccountCreator(ModelValidator modelValidator) {
    this.modelValidator = modelValidator;
  }

  public Account create(String username, String email, String password) {
    final var account = new Account(UUID.randomUUID(), username, email, password);
    return modelValidator.validate(account);
  }

  public Account create(UUID id, String username, String email, String password) {
    final var account = new Account(id, username, email, password);
    return modelValidator.validate(account);
  }
}
