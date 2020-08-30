package com.rpg.model.item;

public abstract class Accessory extends Equipment {
  public Accessory(String name, String description, EquipmentAttributes bonus) {
    super(name, description, bonus);
  }
}
