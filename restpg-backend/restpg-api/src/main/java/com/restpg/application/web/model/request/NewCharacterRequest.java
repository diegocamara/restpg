package com.restpg.application.web.model.request;

import com.restpg.application.web.model.dto.AttributesDTO;
import com.rpg.model.character.Attributes;
import com.rpg.model.character.CharacterClass;
import com.rpg.model.character.NewCharacter;
import lombok.Data;

@Data
public class NewCharacterRequest {

  private String name;
  private AttributesDTO attributes;
  private CharacterClass characterClass;

  public NewCharacter toNewCharacter() {

    return new NewCharacter(
        name,
        new Attributes(
            attributes.getStrength(),
            attributes.getConstitution(),
            attributes.getDexterity(),
            attributes.getIntelligence(),
            attributes.getWisdom(),
            attributes.getCharisma()),
        characterClass);
  }
}
