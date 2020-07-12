package com.rpg.character.model;

import java.math.BigInteger;

public class Experience {
  private final BigInteger current;
  private final BigInteger next;

  private Experience(BigInteger current, BigInteger next) {
    this.current = current;
    this.next = next;
  }

  public static Experience create(BigInteger current, BigInteger next) {
    return new Experience(current, next);
  }

  public BigInteger current() {
    return current;
  }

  public BigInteger next() {
    return next;
  }
}
