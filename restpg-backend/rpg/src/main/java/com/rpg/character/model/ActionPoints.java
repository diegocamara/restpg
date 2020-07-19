package com.rpg.character.model;

import javax.validation.constraints.DecimalMin;
import java.math.BigInteger;

public class ActionPoints {

  @DecimalMin(value = "0", message = "current action point should not be less than 0")
  private final BigInteger current;

  @DecimalMin(value = "10", message = "max current point should not be less than 10")
  private final BigInteger max;

  public ActionPoints(BigInteger current, BigInteger max) {
    this.current = current;
    this.max = max;
  }
}
