package com.rpg.model.character.characterclass;

import com.rpg.model.character.Character;
import com.rpg.model.character.*;
import com.rpg.model.item.Item;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

public class Fighter extends Character {
  public Fighter(
      UUID id,
      Biography biography,
      Integer level,
      ActionPoints healthPoints,
      ActionPoints magicPoints,
      Attributes attributes,
      Experience experience,
      BigInteger gold,
      Type type,
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
        type,
        items,
        equipment,
        skills);
  }

  @Override
  public CharacterClass characterClass() {
    return CharacterClass.FIGHTER;
  }
}
