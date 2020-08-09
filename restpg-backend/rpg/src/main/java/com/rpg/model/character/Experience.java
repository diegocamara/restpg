package com.rpg.model.character;

import javax.validation.constraints.DecimalMin;
import java.math.BigInteger;

public class Experience {
  @DecimalMin(value = "0", message = "current experience should not be less than 0")
  private final BigInteger current;

  @DecimalMin(value = "10", message = "next level experience should not be less than 10")
  private final BigInteger next;

  public Experience(BigInteger current, BigInteger next) {
    this.current = current;
    this.next = next;
  }

  public BigInteger current() {
    return current;
  }

  public BigInteger next() {
    return next;
  }
}
