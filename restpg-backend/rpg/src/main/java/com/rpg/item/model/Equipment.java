package com.rpg.item.model;

public class Equipment extends Item {

  private final EquipmentType equipmentType;

  public Equipment(EquipmentType equipmentType, String name) {
    super(name);
    this.equipmentType = equipmentType;
  }

  public EquipmentType equipmentType() {
    return equipmentType;
  }
}
