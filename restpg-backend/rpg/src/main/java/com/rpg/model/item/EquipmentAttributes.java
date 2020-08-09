package com.rpg.model.item;

import com.rpg.model.character.Attributes;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

public class EquipmentAttributes extends Attributes {

  @Min(value = 1)
  @Max(value = 99)
  @NotNull
  private final BigInteger attack;

  @Min(value = 1)
  @Max(value = 99)
  @NotNull
  private final BigInteger defence;

  public EquipmentAttributes(
      BigInteger strength,
      BigInteger constitution,
      BigInteger dexterity,
      BigInteger intelligence,
      BigInteger wisdom,
      BigInteger charisma,
      BigInteger attack,
      BigInteger defence) {
    super(strength, constitution, dexterity, intelligence, wisdom, charisma);
    this.attack = attack;
    this.defence = defence;
  }

  public BigInteger attack() {
    return attack;
  }

  public BigInteger defence() {
    return defence;
  }
}
