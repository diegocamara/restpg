package com.rpg.model.character.type;

import com.rpg.model.character.Character;
import com.rpg.model.character.*;
import com.rpg.model.item.Item;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

public class Enemy extends Character {

  protected Enemy(
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
        skills);
  }
}
