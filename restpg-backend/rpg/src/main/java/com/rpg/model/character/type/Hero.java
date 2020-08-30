package com.rpg.model.character.type;

import com.rpg.exception.MaximumLevelReachedException;
import com.rpg.model.character.Character;
import com.rpg.model.character.*;
import com.rpg.model.item.Item;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

public abstract class Hero extends Character {

  public static final int MAXIMUM_LEVEL = 99;
  private final HeroClass heroClass;
  private Experience experience;

  protected Hero(
      UUID id,
      Biography biography,
      Integer level,
      ActionPoints healthPoints,
      ActionPoints magicPoints,
      Attributes attributes,
      BigInteger gold,
      List<Item> items,
      CharacterEquipment equipment,
      List<Skill> skills,
      HeroClass heroClass,
      Experience experience) {
    super(
        id,
        biography,
        level,
        healthPoints,
        magicPoints,
        attributes,
        gold,
        items,
        equipment,
        skills);
    this.heroClass = heroClass;
    this.experience = experience;
  }

  @Override
  public void levelUp() {
    level(level() + 1);
    validMaximumLevel();
    applyAttributeBonus();
    experience(Experience.from(level()));
  }

  private void validMaximumLevel() {
    if (level() > MAXIMUM_LEVEL) {
      throw new MaximumLevelReachedException(id());
    }
  }

  protected abstract HeroBonus heroBonus();

  protected abstract BigDecimal baseHealthPoints();

  protected abstract BigDecimal baseMagicPoints();

  public void applyAttributeBonus() {
    applyHealthPointsBonus();
    applyMagicPointsBonus();
  }

  private void applyHealthPointsBonus() {
    final var constitution = heroBonus().constitution();
    final var levelMaxHealthPoints =
        baseHealthPoints()
            .multiply(BigDecimal.valueOf(Math.pow(level().longValue(), constitution.doubleValue())))
            .setScale(0, RoundingMode.FLOOR)
            .toBigInteger();
    heathPoints(new ActionPoints(levelMaxHealthPoints, levelMaxHealthPoints));
  }

  private void applyMagicPointsBonus() {
    var wisdom = heroBonus().wisdom();
    final var levelMaxMagicPoints =
        baseMagicPoints()
            .multiply(BigDecimal.valueOf(Math.pow(level().longValue(), wisdom.doubleValue())))
            .setScale(0, RoundingMode.FLOOR)
            .toBigInteger();
    magicPoints(new ActionPoints(levelMaxMagicPoints, levelMaxMagicPoints));
  }

  public HeroClass heroClass() {
    return heroClass;
  }

  public Experience experience() {
    return experience;
  }

  protected void experience(Experience experience) {
    this.experience = experience;
  }
}
