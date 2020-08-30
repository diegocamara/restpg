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

  @DecimalMin(value = "0")
  private final BigInteger gold;

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
      BigInteger gold,
      List<Item> items,
      CharacterEquipment equipment,
      List<Skill> skills) {
    this.id = id;
    this.biography = biography;
    this.level = level;
    this.healthPoints = healthPoints;
    this.magicPoints = magicPoints;
    this.attributes = attributes;
    this.gold = gold;
    this.items = items;
    this.equipment = equipment;
    this.skills = skills;
  }

  public Stats stats() {
    final var stats =
        new Stats(healthPoints, magicPoints, attributes, attackPower(), defensePower());
    // Apply modifiers
    return stats;
  }

  private BigInteger attackPower() {
    final var characterStrength = attributes.strength();
    return characterStrength.add(equippedAttackSum());
  }

  private BigInteger defensePower() {
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

  protected ActionPoints healthPoints() {
    return healthPoints;
  }

  protected ActionPoints magicPoints() {
    return magicPoints;
  }

  protected Attributes attributes() {
    return attributes;
  }

  public BigInteger gold() {
    return gold;
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
