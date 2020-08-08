package com.restpg.application.context.accountcharacter.model;

import com.rpg.validator.ModelValidator;

import java.util.UUID;

public class AccountCharacterCreator {

  private final ModelValidator modelValidator;

  public AccountCharacterCreator(ModelValidator modelValidator) {
    this.modelValidator = modelValidator;
  }

  public AccountCharacter create(UUID accountId, UUID characterId) {
    return modelValidator.validate(new AccountCharacter(accountId, characterId));
  }
}
