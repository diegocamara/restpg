package com.restpg.domain.hero.exception;

import com.rpg.exception.RPGException;

public class HeroNotFoundException extends RPGException {
  public HeroNotFoundException() {
    super("hero not found");
  }
}
