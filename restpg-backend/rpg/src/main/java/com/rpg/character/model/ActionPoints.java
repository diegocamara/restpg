package com.rpg.character.model;

import java.math.BigInteger;

public class ActionPoints {
  private final BigInteger current;
  private final BigInteger max;

  private ActionPoints(BigInteger current, BigInteger max) {
    this.current = current;
    this.max = max;
  }

  public static ActionPoints create(BigInteger current, BigInteger max) {
    return new ActionPoints(current, max);
  }
}
