package com.restpg.domain.hero.model;

import com.restpg.domain.account.model.Account;
import com.rpg.model.character.type.Hero;
import com.rpg.validator.ModelValidator;

public class AccountHeroCreator {

  private final ModelValidator modelValidator;

  public AccountHeroCreator(ModelValidator modelValidator) {
    this.modelValidator = modelValidator;
  }

  public AccountHero create(Account account, Hero hero) {
    return modelValidator.validate(new AccountHero(account, hero));
  }
}
