package com.restpg.application.web.model.response;

import com.restpg.application.web.model.dto.BiographyDTO;
import com.restpg.application.web.model.dto.ExperienceDTO;
import com.restpg.application.web.model.dto.StatsDTO;
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
  private StatsDTO stats;
  private ExperienceDTO experience;
  private BigInteger gold;
  private HeroClass heroClass;

  public static FindHeroResponse from(Hero hero) {
    final var biography = BiographyDTO.from(hero.biography());
    final var stats = StatsDTO.from(hero.stats());
    final var experience = ExperienceDTO.from(hero.experience());
    return new FindHeroResponse(
        hero.id(), biography, hero.level(), stats, experience, hero.gold(), hero.heroClass());
  }

  //  private final List<Item> items;
  //  private final CharacterEquipment equipment;
  //  private final List<Skill> skills;

}
