package com.rpg.model.character;

import com.rpg.model.character.hero.*;
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
      Biography biography,
      Attributes attributes,
      HeroClass heroClass,
      BigInteger gold,
      Integer level,
      Type type) {

    final var id = UUID.randomUUID();
    final var initialHealthPoints = BigInteger.valueOf(100);
    final var initialMagicPoints = BigInteger.valueOf(20);

    final var character =
        byCharacterClass(
            id,
            biography,
            level,
            new ActionPoints(initialHealthPoints, initialHealthPoints),
            new ActionPoints(initialMagicPoints, initialMagicPoints),
            attributes,
            new Experience(BigInteger.ZERO, BigInteger.valueOf(10)),
            gold,
            heroClass,
            new LinkedList<>(),
            new CharacterEquipment(),
            new LinkedList<>());

    return modelValidator.validate(character);
  }

  public Character create(
      UUID id,
      Biography biography,
      Integer level,
      ActionPoints healthPoints,
      ActionPoints magicPoints,
      Attributes attributes,
      Experience experience,
      BigInteger gold,
      Type type,
      HeroClass heroClass,
      List<Item> items,
      CharacterEquipment characterEquipment,
      List<Skill> skills) {

    final var character =
        byCharacterClass(
            id,
            biography,
            level,
            healthPoints,
            magicPoints,
            attributes,
            experience,
            gold,
            heroClass,
            items,
            characterEquipment,
            skills);

    return modelValidator.validate(character);
  }

  private Character byCharacterClass(
      UUID id,
      Biography biography,
      Integer level,
      ActionPoints healthPoints,
      ActionPoints magicPoints,
      Attributes attributes,
      Experience experience,
      BigInteger gold,
      HeroClass heroClass,
      List<Item> items,
      CharacterEquipment equipment,
      List<Skill> skills) {

    Character character = null;

    switch (heroClass) {
      case FIGHTER:
        character =
            new Fighter(
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
        break;
      case NINJA:
        character =
            new Ninja(
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
        break;
      case WIZARD:
        character =
            new Wizard(
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
        break;
      case RANGER:
        character =
            new Ranger(
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
        break;
      case LANCER:
        character =
            new Lancer(
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
        break;
    }

    return character;
  }
}
