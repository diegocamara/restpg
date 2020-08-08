package com.restpg.application.web.model.request;

import com.rpg.character.model.NewCharacter;
import lombok.Data;

import java.math.BigInteger;

@Data
public class NewCharacterRequest {

  private String name;
  private Attributes attributes;

  public NewCharacter toNewCharacter() {

    return new NewCharacter(
        name,
        new com.rpg.character.model.Attributes(
            attributes.getStrength(), attributes.getAgility(), attributes.getIntelligence()));
  }

  @Data
  static class Attributes {
    private BigInteger strength;
    private BigInteger agility;
    private BigInteger intelligence;
  }
}
