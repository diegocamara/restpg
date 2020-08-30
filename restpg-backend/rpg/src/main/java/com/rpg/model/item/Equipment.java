package com.rpg.model.item;

import com.rpg.model.character.Stats;
import com.rpg.model.modifier.Modifier;

import java.util.List;

public abstract class Equipment extends Item {

  private final EquipmentAttributes bonus;

  public Equipment(String name, String description, EquipmentAttributes bonus) {
    super(name, description);
    this.bonus = bonus;
  }

  public EquipmentAttributes bonus() {
    return bonus;
  }

  public abstract List<Modifier<Stats>> modifiers();
}
