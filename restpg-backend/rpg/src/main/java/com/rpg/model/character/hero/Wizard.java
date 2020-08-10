package com.rpg.model.character.hero;

import com.rpg.model.character.*;
import com.rpg.model.character.type.Hero;
import com.rpg.model.item.Item;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

public class Wizard extends Hero {

  public Wizard(
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
        experience,
        gold,
        items,
        equipment,
        skills,
        HeroClass.WIZARD);
  }
}
