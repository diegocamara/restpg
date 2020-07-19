package com.rpg.character.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigInteger;

public class Attributes {

  @Min(value = 10, message = "strength should not be less than 10")
  @Max(value = 100, message = "strength should not be greater than 100")
  private final BigInteger strength;

  @Min(value = 10, message = "agility should not be less than 10")
  @Max(value = 100, message = "agility should not be greater than 100")
  private final BigInteger agility;

  @Min(value = 10, message = "intelligence should not be less than 10")
  @Max(value = 100, message = "intelligence should not be greater than 100")
  private final BigInteger intelligence;

  public Attributes(BigInteger strength, BigInteger agility, BigInteger intelligence) {
    this.strength = strength;
    this.agility = agility;
    this.intelligence = intelligence;
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
