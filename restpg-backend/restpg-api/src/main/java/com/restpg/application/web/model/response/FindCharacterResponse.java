package com.restpg.application.web.model.response;

import com.restpg.application.web.model.dto.ActionPointsDTO;
import com.restpg.application.web.model.dto.AttributesDTO;
import com.restpg.application.web.model.dto.BiographyDTO;
import com.restpg.application.web.model.dto.ExperienceDTO;
import com.rpg.model.character.Character;
import com.rpg.model.character.CharacterClass;
import com.rpg.model.character.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindCharacterResponse {

  private UUID id;
  private BiographyDTO biography;
  private Integer level;
  private ActionPointsDTO healthPoints;
  private ActionPointsDTO magicPoints;
  private AttributesDTO attributes;
  private ExperienceDTO experience;
  private BigInteger gold;
  private Type type;
  private CharacterClass characterClass;

  public static FindCharacterResponse from(Character character) {
    final var biography = BiographyDTO.from(character.biography());
    final var healthPoints = ActionPointsDTO.from(character.healthPoints());
    final var magicPoints = ActionPointsDTO.from(character.magicPoints());
    final var attributes =
        AttributesDTO.from(
            character.attributes(), character.attackPower(), character.defensePower());
    final var experience = ExperienceDTO.from(character.experience());
    return new FindCharacterResponse(
        character.id(),
        biography,
        character.level(),
        healthPoints,
        magicPoints,
        attributes,
        experience,
        character.gold(),
        character.type(),
        character.characterClass());
  }

  //  private final List<Item> items;
  //  private final CharacterEquipment equipment;
  //  private final List<Skill> skills;

}
