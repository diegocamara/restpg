package com.rpg.exception;

public class CharacterNameAlreadyExistsException extends RPGException {
  public CharacterNameAlreadyExistsException() {
    super("character name already exists");
  }
}
