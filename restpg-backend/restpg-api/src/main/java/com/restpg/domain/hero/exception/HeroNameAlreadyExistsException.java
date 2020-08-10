package com.restpg.domain.hero.exception;

import com.rpg.exception.RPGException;

public class HeroNameAlreadyExistsException extends RPGException {
  public HeroNameAlreadyExistsException() {
    super("hero name already exists");
  }
}
