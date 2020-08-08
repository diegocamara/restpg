package com.rpg.character.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public enum CharacterClass {
  FIGHTER(
      "Fighter",
      "Fighter description",
      new HashMap<>() {
        {
          put(Attribute.STRENGTH, BigDecimal.valueOf(2));
          put(Attribute.CONSTITUTION, BigDecimal.valueOf(1.2));
          put(Attribute.DEXTERITY, BigDecimal.valueOf(1));
          put(Attribute.INTELLIGENCE, BigDecimal.valueOf(1));
          put(Attribute.WISDOM, BigDecimal.valueOf(1));
          put(Attribute.CHARISMA, BigDecimal.valueOf(1));
        }
      }),
  NINJA(
      "Ninja",
      "Ninja description",
      new HashMap<>() {
        {
          put(Attribute.STRENGTH, BigDecimal.valueOf(1));
          put(Attribute.CONSTITUTION, BigDecimal.valueOf(1));
          put(Attribute.DEXTERITY, BigDecimal.valueOf(1.8));
          put(Attribute.INTELLIGENCE, BigDecimal.valueOf(1.4));
          put(Attribute.WISDOM, BigDecimal.valueOf(1.6));
          put(Attribute.CHARISMA, BigDecimal.valueOf(1));
        }
      }),
  WIZARD(
      "Wizard",
      "Wizard description",
      new HashMap<>() {
        {
          put(Attribute.STRENGTH, BigDecimal.valueOf(1));
          put(Attribute.CONSTITUTION, BigDecimal.valueOf(1));
          put(Attribute.DEXTERITY, BigDecimal.valueOf(1.2));
          put(Attribute.INTELLIGENCE, BigDecimal.valueOf(1.8));
          put(Attribute.WISDOM, BigDecimal.valueOf(2));
          put(Attribute.CHARISMA, BigDecimal.valueOf(1.4));
        }
      }),
  RANGER(
      "Ranger",
      "Ranger description",
      new HashMap<>() {
        {
          put(Attribute.STRENGTH, BigDecimal.valueOf(1.2));
          put(Attribute.CONSTITUTION, BigDecimal.valueOf(1.4));
          put(Attribute.DEXTERITY, BigDecimal.valueOf(1.8));
          put(Attribute.INTELLIGENCE, BigDecimal.valueOf(1.2));
          put(Attribute.WISDOM, BigDecimal.valueOf(1.2));
          put(Attribute.CHARISMA, BigDecimal.valueOf(1.4));
        }
      }),
  LANCER(
      "Lancer",
      "Lancer description",
      new HashMap<>() {
        {
          put(Attribute.STRENGTH, BigDecimal.valueOf(1.8));
          put(Attribute.CONSTITUTION, BigDecimal.valueOf(1.6));
          put(Attribute.DEXTERITY, BigDecimal.valueOf(1.4));
          put(Attribute.INTELLIGENCE, BigDecimal.valueOf(1));
          put(Attribute.WISDOM, BigDecimal.valueOf(1));
          put(Attribute.CHARISMA, BigDecimal.valueOf(1));
        }
      });

  private final String name;
  private final String description;
  private final Map<Attribute, BigDecimal> bonus;

  CharacterClass(String name, String description, Map<Attribute, BigDecimal> bonus) {
    this.name = name;
    this.description = description;
    this.bonus = bonus;
  }

  public String getName() {
    return name;
  }

  public String description() {
    return description;
  }

  public Map<Attribute, BigDecimal> attributes() {
    return bonus;
  }
}
