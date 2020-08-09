package com.rpg.model.item;

import com.rpg.model.character.Character;

public abstract class General extends Item {

  public General(String name, String description) {
    super(name, description);
  }

  public abstract void use(Character character);
}
