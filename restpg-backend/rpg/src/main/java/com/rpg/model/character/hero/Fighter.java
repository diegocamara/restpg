package com.rpg.model.character.hero;

import com.rpg.model.character.*;
import com.rpg.model.character.type.Hero;
import com.rpg.model.character.type.HeroBonus;
import com.rpg.model.item.Item;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

public class Fighter extends Hero {

  public static final HeroBonus fighterAttributeBonus;

  static {
    final var strength = BigDecimal.valueOf(4);
    final var constitution = BigDecimal.valueOf(0.2);
    final var dexterity = BigDecimal.valueOf(1);
    final var intelligence = BigDecimal.valueOf(1);
    final var wisdom = BigDecimal.valueOf(1);
    final var charisma = BigDecimal.valueOf(2);
    fighterAttributeBonus =
        new HeroBonus(strength, constitution, dexterity, intelligence, wisdom, charisma);
  }

  public Fighter(
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
        HeroClass.FIGHTER,
        experience);
  }

  @Override
  protected HeroBonus heroBonus() {
    return fighterAttributeBonus;
  }
}
