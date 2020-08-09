package com.restpg.application.web.model.dto;

import com.rpg.model.character.Biography;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BiographyDTO {
  private String name;
  private String background;

  public static BiographyDTO from(Biography biography) {
    return new BiographyDTO(biography.name(), biography.background());
  }

  public Biography to() {
    return new Biography(this.name, this.background);
  }
}
