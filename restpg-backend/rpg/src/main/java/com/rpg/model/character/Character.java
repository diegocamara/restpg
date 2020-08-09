package com.rpg.model.character;

import com.rpg.model.item.Equipment;
import com.rpg.model.item.Item;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Character {

  @NotNull private final UUID id;

  @NotNull @Valid private final Biography biography;

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
    this.id = id;
    this.biography = biography;
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
    final var characterStrength = attributes.strength();
    return characterStrength.add(equippedAttackSum());
  }

  public BigInteger defensePower() {
    final var characterConstitution = attributes.constitution();
    return characterConstitution.add(equippedDefenceSum());
  }

  private BigInteger equippedAttackSum() {
    return equipped().stream()
        .map(equip -> equip.bonus().attack())
        .reduce(BigInteger.ZERO, BigInteger::add);
  }

  private BigInteger equippedDefenceSum() {
    return equipped().stream()
        .map(equip -> equip.bonus().defence())
        .reduce(BigInteger.ZERO, BigInteger::add);
  }

  private List<Equipment> equipped() {
    return Stream.of(equipment.weapon(), equipment.head(), equipment.body(), equipment.accessory())
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  public UUID id() {
    return id;
  }

  public Biography biography() {
    return biography;
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
