package com.rpg.model.item;

public abstract class Equipment extends Item {

  private final EquipmentAttributes bonus;

  public Equipment(String name, String description, EquipmentAttributes bonus) {
    super(name, description);
    this.bonus = bonus;
  }

  public EquipmentAttributes bonus() {
    return bonus;
  }
}
