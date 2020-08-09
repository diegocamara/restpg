package com.restpg.application.web.model.response;

import com.restpg.application.web.model.dto.ActionPointsDTO;
import com.restpg.application.web.model.dto.AttributesDTO;
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
  private String name;
  private Integer level;
  private ActionPointsDTO healthPoints;
  private ActionPointsDTO magicPoints;
  private AttributesDTO attributes;
  private ExperienceDTO experience;
  private BigInteger gold;
  private Type type;
  private CharacterClass characterClass;

  public static FindCharacterResponse from(Character character) {

    final var healthPoints =
        new ActionPointsDTO(character.healthPoints().current(), character.healthPoints().max());
    final var magicPoints =
        new ActionPointsDTO(character.magicPoints().current(), character.magicPoints().max());
    final var characterAttributes = character.attributes();
    final var attributes =
        new AttributesDTO(
            characterAttributes.strength(),
            characterAttributes.constitution(),
            characterAttributes.dexterity(),
            characterAttributes.intelligence(),
            characterAttributes.wisdom(),
            characterAttributes.charisma());

    final var experience =
        new ExperienceDTO(character.experience().current(), character.experience().next());

    return new FindCharacterResponse(
        character.id(),
        character.name(),
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
