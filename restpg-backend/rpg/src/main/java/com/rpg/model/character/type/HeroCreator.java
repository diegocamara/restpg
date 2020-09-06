package com.rpg.model.character.type;

import com.rpg.exception.HeroClassNotProvidedException;
import com.rpg.model.character.*;
import com.rpg.model.character.hero.*;
import com.rpg.model.item.Item;
import com.rpg.validator.ModelValidator;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class HeroCreator {

  private final ModelValidator modelValidator;

  public HeroCreator(ModelValidator modelValidator) {
    this.modelValidator = modelValidator;
  }

  public Hero create(
      HeroClass heroClass,
      Biography biography,
      Attributes attributes,
      BigInteger gold,
      Integer level) {

    validateHeroClass(heroClass);

    final var id = UUID.randomUUID();

    final var experience = Experience.from(level);

    final var hero =
        byHeroClass(
            heroClass,
            id,
            biography,
            level,
            new ActionPoints(),
            new ActionPoints(),
            attributes,
            experience,
            gold,
            new LinkedList<>(),
            new CharacterEquipment(),
            new LinkedList<>());

    hero.applyAttributeBonus();

    return modelValidator.validate(hero);
  }

  private void validateHeroClass(HeroClass heroClass) {
    if (heroClass == null) {
      throw new HeroClassNotProvidedException();
    }
  }

  public Hero create(
      HeroClass heroClass,
      UUID id,
      Biography biography,
      Integer level,
      ActionPoints healthPoints,
      ActionPoints magicPoints,
      Attributes attributes,
      Experience experience,
      BigInteger gold,
      List<Item> items,
      CharacterEquipment characterEquipment,
      List<Skill> skills) {

    final var hero =
        byHeroClass(
            heroClass,
            id,
            biography,
            level,
            healthPoints,
            magicPoints,
            attributes,
            experience,
            gold,
            items,
            characterEquipment,
            skills);

    return modelValidator.validate(hero);
  }

  public Hero byHeroClass(
      HeroClass heroClass,
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

    Hero hero = null;

    switch (heroClass) {
      case FIGHTER:
        hero =
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
        hero =
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
        hero =
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
        hero =
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
        hero =
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

    return hero;
  }
}
