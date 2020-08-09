package com.restpg.application.web.model.dto;

import com.rpg.model.character.Attributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributesDTO {
  private BigInteger strength;
  private BigInteger constitution;
  private BigInteger dexterity;
  private BigInteger intelligence;
  private BigInteger wisdom;
  private BigInteger charisma;
  private BigInteger attackPower;
  private BigInteger defencePower;

  public static AttributesDTO from(
      Attributes attributes, BigInteger attackPower, BigInteger defensePower) {
    return new AttributesDTO(
        attributes.strength(),
        attributes.constitution(),
        attributes.dexterity(),
        attributes.intelligence(),
        attributes.wisdom(),
        attributes.charisma(),
        attackPower,
        defensePower);
  }

  public Attributes to() {
    return new Attributes(strength, constitution, dexterity, intelligence, wisdom, charisma);
  }
}
