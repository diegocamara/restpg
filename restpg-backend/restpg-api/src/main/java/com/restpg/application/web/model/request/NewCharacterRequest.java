package com.restpg.application.web.model.request;

import com.rpg.account.model.Account;
import com.rpg.character.model.NewCharacter;
import lombok.Data;

import java.math.BigInteger;

@Data
public class NewCharacterRequest {

  private String name;
  private Attributes attributes;

  public NewCharacter toNewCharacter(Account account) {

    return new NewCharacter(
        account,
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
