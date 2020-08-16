package com.rpg.model.character;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Experience {

  private static final double exponent = 1.2;
  private static final BigDecimal baseExperience = BigDecimal.valueOf(10);

  @DecimalMin(value = "0", message = "current experience should not be less than 0")
  private final BigInteger current;

  @DecimalMin(value = "10", message = "next level experience should not be less than 10")
  private final BigInteger next;

  public Experience(BigInteger current, BigInteger next) {
    this.current = current;
    this.next = next;
  }

  public static Experience from(double level) {
    return new Experience(BigInteger.ZERO, nextLevel(level));
  }

  public static BigInteger nextLevel(double level) {
    return baseExperience
        .multiply(BigDecimal.valueOf(Math.pow(level, exponent)))
        .setScale(0, RoundingMode.FLOOR)
        .toBigInteger();
  }

  public BigInteger current() {
    return current;
  }

  public BigInteger next() {
    return next;
  }
}
