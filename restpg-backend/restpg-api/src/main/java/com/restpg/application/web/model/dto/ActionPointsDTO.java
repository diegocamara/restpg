package com.restpg.application.web.model.dto;

import com.rpg.model.character.ActionPoints;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionPointsDTO {
  private BigInteger current;
  private BigInteger max;

  public static ActionPointsDTO from(ActionPoints actionPoints) {
    return new ActionPointsDTO(actionPoints.current(), actionPoints.max());
  }
}
