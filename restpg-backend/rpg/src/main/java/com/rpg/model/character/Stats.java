package com.rpg.model.character;

import java.math.BigInteger;

public class Stats {

  private ActionPoints healthPoints;
  private ActionPoints magicPoints;
  private Attributes attributes;
  private BigInteger attackPower;
  private BigInteger defensePower;

  protected Stats(
      ActionPoints healthPoints,
      ActionPoints magicPoints,
      Attributes attributes,
      BigInteger attackPower,
      BigInteger defensePower) {
    this.healthPoints = healthPoints;
    this.magicPoints = magicPoints;
    this.attributes = attributes;
    this.attackPower = attackPower;
    this.defensePower = defensePower;
  }

  public ActionPoints healthPoints() {
    return healthPoints;
  }

  public ActionPoints magicPoints() {
    return magicPoints;
  }

  public Attributes attributes() {
    return attributes;
  }

  public BigInteger attackPower() {
    return attackPower;
  }

  public BigInteger defensePower() {
    return defensePower;
  }

  protected void healthPoints(ActionPoints healthPoints) {
    this.healthPoints = healthPoints;
  }

  protected void magicPoints(ActionPoints magicPoints) {
    this.magicPoints = magicPoints;
  }

  protected void attributes(Attributes attributes) {
    this.attributes = attributes;
  }

  protected void attackPower(BigInteger attackPower) {
    this.attackPower = attackPower;
  }

  protected void defensePower(BigInteger defensePower) {
    this.defensePower = defensePower;
  }
}
