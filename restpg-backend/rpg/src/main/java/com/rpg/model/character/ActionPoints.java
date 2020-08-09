package com.rpg.model.character;

import javax.validation.constraints.DecimalMin;
import java.math.BigInteger;

public class ActionPoints {

  @DecimalMin(value = "0")
  private final BigInteger current;

  @DecimalMin(value = "1")
  private final BigInteger max;

  public ActionPoints(BigInteger current, BigInteger max) {
    this.current = current;
    this.max = max;
  }

  public BigInteger current() {
    return current;
  }

  public BigInteger max() {
    return max;
  }
}
