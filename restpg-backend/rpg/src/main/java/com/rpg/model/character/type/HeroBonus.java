package com.rpg.model.character.type;

import java.math.BigDecimal;

public class HeroBonus {

  private final BigDecimal strength;
  private final BigDecimal constitution;
  private final BigDecimal dexterity;
  private final BigDecimal intelligence;
  private final BigDecimal wisdom;
  private final BigDecimal charisma;

  public HeroBonus(
      BigDecimal strength,
      BigDecimal constitution,
      BigDecimal dexterity,
      BigDecimal intelligence,
      BigDecimal wisdom,
      BigDecimal charisma) {
    this.strength = strength;
    this.constitution = constitution;
    this.dexterity = dexterity;
    this.intelligence = intelligence;
    this.wisdom = wisdom;
    this.charisma = charisma;
  }

  public BigDecimal strength() {
    return strength;
  }

  public BigDecimal constitution() {
    return constitution;
  }

  public BigDecimal dexterity() {
    return dexterity;
  }

  public BigDecimal intelligence() {
    return intelligence;
  }

  public BigDecimal wisdom() {
    return wisdom;
  }

  public BigDecimal charisma() {
    return charisma;
  }
}
