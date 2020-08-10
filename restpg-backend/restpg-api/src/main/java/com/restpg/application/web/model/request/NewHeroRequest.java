package com.restpg.application.web.model.request;

import com.restpg.application.web.model.dto.AttributesDTO;
import com.restpg.application.web.model.dto.BiographyDTO;
import com.rpg.model.character.HeroClass;
import com.rpg.model.character.NewHero;
import lombok.Data;

@Data
public class NewHeroRequest {

  private BiographyDTO biography;
  private AttributesDTO attributes;
  private HeroClass heroClass;

  public NewHero toNewHero() {
    return new NewHero(biography.to(), attributes.to(), heroClass);
  }
}
