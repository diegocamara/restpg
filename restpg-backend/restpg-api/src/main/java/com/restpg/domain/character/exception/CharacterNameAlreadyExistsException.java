package com.restpg.domain.character.exception;

import com.rpg.exception.RPGException;

public class CharacterNameAlreadyExistsException extends RPGException {
  public CharacterNameAlreadyExistsException() {
    super("character name already exists");
  }
}
