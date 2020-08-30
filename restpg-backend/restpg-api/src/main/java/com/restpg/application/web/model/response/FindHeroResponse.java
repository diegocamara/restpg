package com.restpg.application.web.model.response;

import com.restpg.application.web.model.dto.ActionPointsDTO;
import com.restpg.application.web.model.dto.AttributesDTO;
import com.restpg.application.web.model.dto.BiographyDTO;
import com.restpg.application.web.model.dto.ExperienceDTO;
import com.rpg.model.character.HeroClass;
import com.rpg.model.character.type.Hero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindHeroResponse {

  private UUID id;
  private BiographyDTO biography;
  private Integer level;
  private ActionPointsDTO healthPoints;
  private ActionPointsDTO magicPoints;
  private AttributesDTO attributes;
  private ExperienceDTO experience;
  private BigInteger gold;
  private HeroClass heroClass;

  public static FindHeroResponse from(Hero hero) {
    final var biography = BiographyDTO.from(hero.biography());
    final var stats = hero.stats();
    final var healthPoints = ActionPointsDTO.from(stats.healthPoints());
    final var magicPoints = ActionPointsDTO.from(stats.magicPoints());
    final var attributes =
        AttributesDTO.from(stats.attributes(), stats.attackPower(), stats.defensePower());
    final var experience = ExperienceDTO.from(hero.experience());
    return new FindHeroResponse(
        hero.id(),
        biography,
        hero.level(),
        healthPoints,
        magicPoints,
        attributes,
        experience,
        hero.gold(),
        hero.heroClass());
  }

  //  private final List<Item> items;
  //  private final CharacterEquipment equipment;
  //  private final List<Skill> skills;

}
