package com.rpg.model.character;

import com.rpg.model.character.characterclass.*;
import com.rpg.model.item.Item;
import com.rpg.validator.ModelValidator;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class CharacterCreator {

  private final ModelValidator modelValidator;

  public CharacterCreator(ModelValidator modelValidator) {
    this.modelValidator = modelValidator;
  }

  public Character create(
      String name,
      Attributes attributes,
      Type type,
      CharacterClass characterClass,
      BigInteger gold,
      Integer level) {

    final var id = UUID.randomUUID();
    final var initialHealthPoints = BigInteger.valueOf(100);
    final var initialMagicPoints = BigInteger.valueOf(20);

    final var character =
        byCharacterClass(
            id,
            name,
            level,
            new ActionPoints(initialHealthPoints, initialHealthPoints),
            new ActionPoints(initialMagicPoints, initialMagicPoints),
            attributes,
            new Experience(BigInteger.ZERO, BigInteger.valueOf(10)),
            gold,
            type,
            characterClass,
            new LinkedList<>(),
            new CharacterEquipment(),
            new LinkedList<>());

    return modelValidator.validate(character);
  }

  public Character create(
      UUID id,
      String name,
      Integer level,
      ActionPoints healthPoints,
      ActionPoints magicPoints,
      Attributes attributes,
      Experience experience,
      BigInteger gold,
      Type type,
      CharacterClass characterClass,
      List<Item> items,
      CharacterEquipment characterEquipment,
      List<Skill> skills) {

    final var character =
        byCharacterClass(
            id,
            name,
            level,
            healthPoints,
            magicPoints,
            attributes,
            experience,
            gold,
            type,
            characterClass,
            items,
            characterEquipment,
            skills);

    return modelValidator.validate(character);
  }

  private Character byCharacterClass(
      UUID id,
      String name,
      Integer level,
      ActionPoints healthPoints,
      ActionPoints magicPoints,
      Attributes attributes,
      Experience experience,
      BigInteger gold,
      Type type,
      CharacterClass characterClass,
      List<Item> items,
      CharacterEquipment equipment,
      List<Skill> skills) {

    Character character = null;

    switch (characterClass) {
      case FIGHTER:
        character =
            new Fighter(
                id,
                name,
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
        break;
      case NINJA:
        character =
            new Ninja(
                id,
                name,
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
        break;
      case WIZARD:
        character =
            new Wizard(
                id,
                name,
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
        break;
      case RANGER:
        character =
            new Ranger(
                id,
                name,
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
        break;
      case LANCER:
        character =
            new Lancer(
                id,
                name,
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
        break;
    }

    return character;
  }
}
