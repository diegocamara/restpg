package com.restpg.application.web.model.dto;

import com.rpg.model.character.Experience;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceDTO {
  private BigInteger current;
  private BigInteger next;

  public static ExperienceDTO from(Experience experience) {
    return new ExperienceDTO(experience.current(), experience.next());
  }
}
