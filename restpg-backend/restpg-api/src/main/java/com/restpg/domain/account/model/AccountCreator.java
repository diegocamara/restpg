package com.restpg.domain.account.model;

import com.rpg.validator.ModelValidator;

import java.util.UUID;

import static java.util.Arrays.asList;

public class AccountCreator {

  private final ModelValidator modelValidator;

  public AccountCreator(ModelValidator modelValidator) {
    this.modelValidator = modelValidator;
  }

  public Account create(String username, String email, String password, Role... roles) {
    final var account = new Account(UUID.randomUUID(), username, email, password, asList(roles));
    return modelValidator.validate(account);
  }

  public Account create(UUID id, String username, String email, String password, Role... roles) {
    final var account = new Account(id, username, email, password, asList(roles));
    return modelValidator.validate(account);
  }
}
