package com.rpg.factory;

import com.rpg.model.character.Character;
import com.rpg.model.character.NewCharacter;

public class CharacterFactory implements AbstractFactory<NewCharacter, Character> {
  @Override
  public Character create(NewCharacter newCharacter) {
    return null;
  }
}
