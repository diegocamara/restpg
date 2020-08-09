package com.rpg.model.character;

import javax.validation.constraints.Size;

public class Biography {

  @Size(min = 4, max = 20)
  private final String name;

  @Size(min = 4, max = 20)
  private final String background;

  public Biography(String name, String background) {
    this.name = name;
    this.background = background;
  }

  public String name() {
    return name;
  }

  public String background() {
    return background;
  }
}
