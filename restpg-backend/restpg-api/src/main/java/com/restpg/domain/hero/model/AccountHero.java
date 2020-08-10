package com.restpg.domain.hero.model;

import com.restpg.domain.account.model.Account;
import com.rpg.model.character.type.Hero;

import javax.validation.constraints.NotNull;

public class AccountHero {
  @NotNull private final Account account;
  @NotNull private final Hero hero;

  protected AccountHero(Account account, Hero hero) {
    this.account = account;
    this.hero = hero;
  }

  public Account account() {
    return account;
  }

  public Hero hero() {
    return hero;
  }
}
