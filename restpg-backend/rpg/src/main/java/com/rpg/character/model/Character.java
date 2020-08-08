package com.rpg.character.model;

import com.rpg.item.model.Equipment;
import com.rpg.item.model.EquipmentType;
import com.rpg.item.model.Item;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Character {

  @NotNull private final UUID id;

  @Size(min = 4, max = 20, message = "name must be between 4 and 20 characters")
  private final String name;

  @Min(value = 1, message = "level should not be less than 1")
  @Max(value = 100, message = "level should not be greater than 100")
  private final Integer level;

  @NotNull @Valid private final ActionPoints healthPoints;
  @NotNull @Valid private final ActionPoints magicPoints;
  @NotNull private final Map<Attribute, BigInteger> attributes;
  @NotNull @Valid private final Experience experience;

  @DecimalMin(value = "0", message = "gold should not be less than 0")
  private final BigInteger gold;

  private final Type type;
  private final CharacterClass characterClass;
  private final List<Item> items;
  private final Map<EquipmentType, Equipment> equipment;
  private final List<Skill> skills;

  protected Character(
      UUID id,
      String name,
      Integer level,
      ActionPoints healthPoints,
      ActionPoints magicPoints,
      Map<Attribute, BigInteger> attributes,
      Experience experience,
      BigInteger gold,
      Type type,
      CharacterClass characterClass,
      List<Item> items,
      Map<EquipmentType, Equipment> equipment,
      List<Skill> skills) {
    this.id = id;
    this.name = name;
    this.level = level;
    this.healthPoints = healthPoints;
    this.magicPoints = magicPoints;
    this.attributes = attributes;
    this.experience = experience;
    this.gold = gold;
    this.type = type;
    this.characterClass = characterClass;
    this.items = items;
    this.equipment = equipment;
    this.skills = skills;
  }

  public UUID id() {
    return id;
  }

  public String name() {
    return name;
  }

  public Integer level() {
    return level;
  }

  public ActionPoints healthPoints() {
    return healthPoints;
  }

  public ActionPoints magicPoints() {
    return magicPoints;
  }

  public Map<Attribute, BigInteger> attributes() {
    return attributes;
  }

  public Experience experience() {
    return experience;
  }

  public BigInteger gold() {
    return gold;
  }

  public Type type() {
    return type;
  }

  public CharacterClass characterClass() {
    return characterClass;
  }

  public List<Item> items() {
    return items;
  }

  public Map<EquipmentType, Equipment> equipment() {
    return equipment;
  }

  public List<Skill> skills() {
    return skills;
  }
}
