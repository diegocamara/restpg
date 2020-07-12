package com.rpg.character.model;

import java.math.BigInteger;

public class Attributes {
  private final BigInteger strength;
  private final BigInteger agility;
  private final BigInteger intelligence;

  private Attributes(BigInteger strength, BigInteger agility, BigInteger intelligence) {
    this.strength = strength;
    this.agility = agility;
    this.intelligence = intelligence;
  }

  public static Attributes create(
      BigInteger strength, BigInteger agility, BigInteger intelligence) {
    return new Attributes(strength, agility, intelligence);
  }

  public BigInteger strength() {
    return strength;
  }

  public BigInteger agility() {
    return agility;
  }

  public BigInteger intelligence() {
    return intelligence;
  }
}
