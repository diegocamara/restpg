package com.rpg.character.model;

import com.rpg.account.model.Account;

import java.math.BigInteger;
import java.util.UUID;

public class Character {
  private final UUID id;
  private final Account account;
  private final String name;
  private final Integer level;
  private final ActionPoints healthPoints;
  private final ActionPoints magicPoints;
  private final Attributes attributes;
  private final Experience experience;
  private final BigInteger gold;

  public Character(
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

  public static Character create(Account account, String name, Attributes attributes) {
    final var initialHealthPoints = BigInteger.valueOf(100);
    final var initialMagicPoints = BigInteger.valueOf(20);
    final var initialLevel = 1;
    return new Character(
        UUID.randomUUID(),
        account,
        name,
        initialLevel,
        ActionPoints.create(initialHealthPoints, initialHealthPoints),
        ActionPoints.create(initialMagicPoints, initialMagicPoints),
        attributes,
        Experience.create(BigInteger.ZERO, BigInteger.valueOf(100)),
        BigInteger.ZERO);
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
