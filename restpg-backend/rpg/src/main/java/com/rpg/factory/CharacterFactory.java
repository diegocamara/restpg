package com.rpg.factory;

import com.rpg.model.character.Character;
import com.rpg.model.character.NewHero;

public class CharacterFactory implements AbstractFactory<NewHero, Character> {
  @Override
  public Character create(NewHero newHero) {
    return null;
  }
}
