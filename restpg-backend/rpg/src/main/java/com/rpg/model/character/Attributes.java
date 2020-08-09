package com.rpg.model.character;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

public class Attributes {

  @Min(value = 1)
  @Max(value = 99)
  @NotNull
  private final BigInteger strength;

  @Min(value = 1)
  @Max(value = 99)
  @NotNull
  private final BigInteger constitution;

  @Min(value = 1)
  @Max(value = 99)
  @NotNull
  private final BigInteger dexterity;

  @Min(value = 1)
  @Max(value = 99)
  @NotNull
  private final BigInteger intelligence;

  @Min(value = 1)
  @Max(value = 99)
  @NotNull
  private final BigInteger wisdom;

  @Min(value = 1)
  @Max(value = 99)
  @NotNull
  private final BigInteger charisma;

  public Attributes(
      BigInteger strength,
      BigInteger constitution,
      BigInteger dexterity,
      BigInteger intelligence,
      BigInteger wisdom,
      BigInteger charisma) {
    this.strength = strength;
    this.constitution = constitution;
    this.dexterity = dexterity;
    this.intelligence = intelligence;
    this.wisdom = wisdom;
    this.charisma = charisma;
  }

  public BigInteger strength() {
    return strength;
  }

  public BigInteger constitution() {
    return constitution;
  }

  public BigInteger dexterity() {
    return dexterity;
  }

  public BigInteger intelligence() {
    return intelligence;
  }

  public BigInteger wisdom() {
    return wisdom;
  }

  public BigInteger charisma() {
    return charisma;
  }
}
