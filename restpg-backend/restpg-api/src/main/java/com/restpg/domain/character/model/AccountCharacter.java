package com.restpg.domain.character.model;

import com.restpg.domain.account.model.Account;
import com.rpg.model.character.Character;

import javax.validation.constraints.NotNull;

public class AccountCharacter {
  @NotNull private final Account account;
  @NotNull private final Character character;

  protected AccountCharacter(Account account, Character character) {
    this.account = account;
    this.character = character;
  }

  public Account account() {
    return account;
  }

  public Character character() {
    return character;
  }
}
