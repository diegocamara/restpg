package com.restpg.application.web.model.dto;

import com.rpg.model.character.Stats;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsDTO {
  private ActionPointsDTO healthPoints;
  private ActionPointsDTO magicPoints;
  private AttributesDTO attributes;

  public static StatsDTO from(Stats stats) {
    final var healthPoints = ActionPointsDTO.from(stats.healthPoints());
    final var magicPoints = ActionPointsDTO.from(stats.magicPoints());
    final var attributes =
        AttributesDTO.from(stats.attributes(), stats.attackPower(), stats.defensePower());
    return new StatsDTO(healthPoints, magicPoints, attributes);
  }
}
