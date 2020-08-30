package com.rpg.model.character;

import com.rpg.model.item.Equipment;
import com.rpg.model.item.Item;
import com.rpg.model.modifier.Modifier;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.LinkedList;
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
  private Integer level;

  @NotNull @Valid private ActionPoints healthPoints;
  @NotNull @Valid private ActionPoints magicPoints;
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

  public abstract void levelUp();

  public Stats stats() {
    final var stats =
        new Stats(healthPoints, magicPoints, attributes, attackPower(), defensePower());
    final var equippedModifiers = equippedModifiers();
    equippedModifiers.forEach(statsModifier -> statsModifier.apply(stats));
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

  private List<Modifier<Stats>> equippedModifiers() {
    return equipped().stream()
        .map(Equipment::modifiers)
        .reduce(
            new LinkedList<>(),
            (partialModifiers, modifiers) -> {
              partialModifiers.addAll(modifiers);
              return partialModifiers;
            });
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

  protected void level(Integer level) {
    this.level = level;
  }

  protected ActionPoints healthPoints() {
    return healthPoints;
  }

  protected void heathPoints(ActionPoints healthPoints) {
    this.healthPoints = healthPoints;
  }

  protected ActionPoints magicPoints() {
    return magicPoints;
  }

  protected void magicPoints(ActionPoints magicPoints) {
    this.magicPoints = magicPoints;
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
