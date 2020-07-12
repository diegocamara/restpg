package com.rpg.character.model;

import com.rpg.account.model.Account;

public class NewCharacter {
  private final Account account;
  private final String name;
  private final Attributes attributes;

  public NewCharacter(Account account, String name, Attributes attributes) {
    this.account = account;
    this.name = name;
    this.attributes = attributes;
  }

  public Account account() {
    return account;
  }

  public String name() {
    return name;
  }

  public Attributes attributes() {
    return attributes;
  }
}
