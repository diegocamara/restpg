package com.rpg.exception;

public class HeroClassNotProvidedException extends RPGException {
  public HeroClassNotProvidedException() {
    super("Hero class not provided");
  }
}
