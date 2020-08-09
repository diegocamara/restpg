package com.rpg.model.character;

import com.rpg.model.item.Item;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

public abstract class Character {

  @NotNull private final UUID id;

  @Size(min = 4, max = 20)
  private final String name;

  @Min(value = 1)
  @Max(value = 100)
  private final Integer level;

  @NotNull @Valid private final ActionPoints healthPoints;
  @NotNull @Valid private final ActionPoints magicPoints;
  @NotNull @Valid private final Attributes attributes;
  @NotNull @Valid private final Experience experience;

  @DecimalMin(value = "0")
  private final BigInteger gold;

  @NotNull private final Type type;

  @NotNull private final List<Item> items;
  @NotNull private final CharacterEquipment equipment;
  @NotNull private final List<Skill> skills;

  public Character(
      UUID id,
      String name,
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
    this.id = id;
    this.name = name;
    this.level = level;
    this.healthPoints = healthPoints;
    this.magicPoints = magicPoints;
    this.attributes = attributes;
    this.experience = experience;
    this.gold = gold;
    this.type = type;
    this.items = items;
    this.equipment = equipment;
    this.skills = skills;
  }

  public abstract CharacterClass characterClass();

  public BigInteger attackPower() {
    final var totalCharacterStrength = attributes.strength();
    final var weaponEquipmentAttack = equipment.weapon().bonus().attack();
    final var headEquipmentAttack = equipment.head().bonus().attack();
    final var bodyEquipmentAttack = equipment.body().bonus().attack();
    final var accessoryEquipmentAttack = equipment.accessory().bonus().attack();
    return totalCharacterStrength
        .add(weaponEquipmentAttack)
        .add(headEquipmentAttack)
        .add(bodyEquipmentAttack)
        .add(accessoryEquipmentAttack);
  }

  public BigInteger defensePower() {
    // TODO
    return null;
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

  public Attributes attributes() {
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

  public List<Item> items() {
    return items;
  }

  public CharacterEquipment equipment() {
    return equipment;
  }

  public List<Skill> skills() {
    return skills;
  }
}
