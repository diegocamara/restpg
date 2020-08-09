package com.restpg.domain.character.model;

import com.restpg.domain.account.model.Account;
import com.rpg.model.character.Character;
import com.rpg.validator.ModelValidator;

public class AccountCharacterCreator {

  private final ModelValidator modelValidator;

  public AccountCharacterCreator(ModelValidator modelValidator) {
    this.modelValidator = modelValidator;
  }

  public AccountCharacter create(Account account, Character character) {
    return modelValidator.validate(new AccountCharacter(account, character));
  }
}
