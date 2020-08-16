package com.rpg.model.character;

import javax.validation.constraints.DecimalMin;
import java.math.BigInteger;

public class ActionPoints {

  @DecimalMin(value = "0")
  private BigInteger current;

  @DecimalMin(value = "1")
  private BigInteger max;

  public ActionPoints(BigInteger current, BigInteger max) {
    this.current = current;
    this.max = max;
  }

  public ActionPoints() {
    this.current = BigInteger.ZERO;
    this.max = BigInteger.ZERO;
  }

  public BigInteger current() {
    return current;
  }

  public void addToCurrent(BigInteger value) {
    this.current = this.current.add(value);
  }

  public BigInteger max() {
    return max;
  }

  public void addToMax(BigInteger value) {
    this.max = this.max.add(value);
  }
}
