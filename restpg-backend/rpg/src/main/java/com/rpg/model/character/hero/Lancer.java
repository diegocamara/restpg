package com.rpg.model.character.hero;

import com.rpg.model.character.*;
import com.rpg.model.character.type.Hero;
import com.rpg.model.character.type.HeroBonus;
import com.rpg.model.item.Item;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

public class Lancer extends Hero {

  public static final HeroBonus attributeBonus;
  public static final BigDecimal baseHealthPoints = BigDecimal.valueOf(80);
  public static final BigDecimal baseMagicPoints = BigDecimal.valueOf(10);

  static {
    final var strength = BigDecimal.valueOf(0.7);
    final var constitution = BigDecimal.valueOf(0.5);
    final var dexterity = BigDecimal.valueOf(0.5);
    final var intelligence = BigDecimal.valueOf(0.6);
    final var wisdom = BigDecimal.valueOf(0.4);
    final var charisma = BigDecimal.valueOf(0.5);
    attributeBonus =
        new HeroBonus(strength, constitution, dexterity, intelligence, wisdom, charisma);
  }

  public Lancer(
      UUID id,
      Biography biography,
      Integer level,
      ActionPoints healthPoints,
      ActionPoints magicPoints,
      Attributes attributes,
      Experience experience,
      BigInteger gold,
      List<Item> items,
      CharacterEquipment equipment,
      List<Skill> skills) {
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
        skills,
        HeroClass.LANCER,
        experience);
  }

  @Override
  protected HeroBonus heroBonus() {
    return attributeBonus;
  }

  @Override
  protected BigDecimal baseHealthPoints() {
    return baseHealthPoints;
  }

  @Override
  protected BigDecimal baseMagicPoints() {
    return baseMagicPoints;
  }
}
