package com.restpg.application.web.model.request;

import com.restpg.application.web.model.dto.AttributesDTO;
import com.restpg.application.web.model.dto.BiographyDTO;
import com.rpg.model.character.CharacterClass;
import com.rpg.model.character.NewCharacter;
import com.rpg.model.character.Type;
import lombok.Data;

@Data
public class NewCharacterRequest {

  private BiographyDTO biography;
  private AttributesDTO attributes;
  private CharacterClass characterClass;
  private Type type;

  public NewCharacter toNewCharacter() {
    return new NewCharacter(biography.to(), attributes.to(), characterClass);
  }
}
