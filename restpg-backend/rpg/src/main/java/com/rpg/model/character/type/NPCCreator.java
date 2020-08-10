package com.rpg.model.character.type;

import com.rpg.model.character.*;
import com.rpg.model.item.Item;
import com.rpg.validator.ModelValidator;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

public class NPCCreator {

  private final ModelValidator modelValidator;

  public NPCCreator(ModelValidator modelValidator) {
    this.modelValidator = modelValidator;
  }

  public NPC create(
      Biography biography,
      Attributes attributes,
      BigInteger gold,
      Integer level,
      List<Item> items,
      CharacterEquipment equipment,
      List<Skill> skills) {

    final var id = UUID.randomUUID();
    final var initialHealthPoints = BigInteger.valueOf(100);
    final var initialMagicPoints = BigInteger.valueOf(20);
    final var healthPoints = new ActionPoints(initialHealthPoints, initialHealthPoints);
    final var magicPoints = new ActionPoints(initialMagicPoints, initialMagicPoints);

    final var npc =
        new NPC(
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

    return modelValidator.validate(npc);
  }

  public NPC create(
      UUID id,
      Biography biography,
      Integer level,
      ActionPoints healthPoints,
      ActionPoints magicPoints,
      Attributes attributes,
      BigInteger gold,
      List<Item> items,
      CharacterEquipment equipment,
      List<Skill> skills) {

    final var npc =
        new NPC(
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

    return modelValidator.validate(npc);
  }
}
