package com.rpg.model.character;

public enum CharacterClass {
  FIGHTER("Fighter", "Fighter description"),
  NINJA("Ninja", "Ninja description"),
  WIZARD("Wizard", "Wizard description"),
  RANGER("Ranger", "Ranger description"),
  LANCER("Lancer", "Lancer description");

  private final String name;
  private final String description;

  CharacterClass(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public String description() {
    return description;
  }
}
