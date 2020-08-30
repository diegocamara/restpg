package com.rpg.model.character.type;

import com.rpg.model.character.Character;
import com.rpg.model.character.*;
import com.rpg.model.item.Item;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

public abstract class Hero extends Character {

  private final HeroClass heroClass;
  private final Experience experience;

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
            .multiply(BigDecimal.valueOf(Math.pow(level().longValue(), constitution.longValue())))
            .setScale(0, RoundingMode.FLOOR)
            .toBigInteger();
    final var currentHealthPoints = healthPoints();
    currentHealthPoints.addToCurrent(levelMaxHealthPoints);
    currentHealthPoints.addToMax(levelMaxHealthPoints);
  }

  private void applyMagicPointsBonus() {
    var wisdom = heroBonus().wisdom();
    final var levelMaxMagicPoints =
        baseMagicPoints()
            .multiply(BigDecimal.valueOf(Math.pow(level().longValue(), wisdom.longValue())))
            .setScale(0, RoundingMode.FLOOR)
            .toBigInteger();
    final var currentMagicPoints = magicPoints();
    currentMagicPoints.addToCurrent(levelMaxMagicPoints);
    currentMagicPoints.addToMax(levelMaxMagicPoints);
  }

  //  protected abstract List<Modifier<Hero>> classModifiers();
  //
  //  private void applyModifiers() {
  //    classModifiers().forEach(classModifier -> classModifier.apply(this));
  //  }

  public HeroClass heroClass() {
    return heroClass;
  }

  public Experience experience() {
    return experience;
  }
}
