package com.rpg.model.character.hero;

import com.rpg.model.character.*;
import com.rpg.model.character.type.Hero;
import com.rpg.model.character.type.HeroBonus;
import com.rpg.model.item.Item;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

public class Ninja extends Hero {

  public Ninja(
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
        HeroClass.NINJA,
        experience);
  }

  @Override
  protected HeroBonus heroBonus() {
    return null;
  }

  @Override
  protected BigDecimal baseHealthPoints() {
    return null;
  }

  @Override
  protected BigDecimal baseMagicPoints() {
    return null;
  }
}
