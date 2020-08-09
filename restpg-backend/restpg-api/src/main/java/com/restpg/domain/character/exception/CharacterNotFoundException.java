package com.restpg.domain.character.exception;

import com.rpg.exception.RPGException;

public class CharacterNotFoundException extends RPGException {
  public CharacterNotFoundException() {
    super("character not found");
  }
}
