package com.rpg.exception;

import java.util.UUID;

import static java.lang.String.format;

public class MaximumLevelReachedException extends RPGException {
  public MaximumLevelReachedException(UUID characterId) {
    super(format("maximum level reached for the character of id: %s", characterId));
  }
}
