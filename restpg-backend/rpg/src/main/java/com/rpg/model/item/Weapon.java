package com.rpg.model.item;

public abstract class Weapon extends Equipment {
  public Weapon(String name, String description, EquipmentAttributes bonus) {
    super(name, description, bonus);
  }
}
