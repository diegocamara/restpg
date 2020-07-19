package com.rpg.character.model;

import com.rpg.account.model.Account;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigInteger;
import java.util.UUID;

public class Character {
  @NotNull private final UUID id;
  @NotNull private final Account account;

  @Size(min = 4, max = 20, message = "name must be between 4 and 20 characters")
  private final String name;

  @Min(value = 1, message = "level should not be less than 1")
  @Max(value = 100, message = "level should not be greater than 100")
  private final Integer level;

  @NotNull @Valid private final ActionPoints healthPoints;
  @NotNull @Valid private final ActionPoints magicPoints;
  @NotNull @Valid private final Attributes attributes;
  @NotNull @Valid private final Experience experience;

  @DecimalMin(value = "0", message = "gold should not be less than 0")
  private final BigInteger gold;

  protected Character(
      UUID id,
      Account account,
      String name,
      Integer level,
      ActionPoints healthPoints,
      ActionPoints magicPoints,
      Attributes attributes,
      Experience experience,
      BigInteger gold) {
    this.id = id;
    this.account = account;
    this.name = name;
    this.level = level;
    this.healthPoints = healthPoints;
    this.magicPoints = magicPoints;
    this.attributes = attributes;
    this.experience = experience;
    this.gold = gold;
  }

  public UUID id() {
    return id;
  }

  public Account account() {
    return account;
  }

  public String name() {
    return name;
  }

  public Integer level() {
    return level;
  }

  public ActionPoints healthPoints() {
    return healthPoints;
  }

  public ActionPoints magicPoints() {
    return magicPoints;
  }

  public Attributes attributes() {
    return attributes;
  }

  public Experience experience() {
    return experience;
  }

  public BigInteger gold() {
    return gold;
  }
}
