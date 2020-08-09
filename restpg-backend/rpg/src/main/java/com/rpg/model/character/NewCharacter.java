package com.rpg.model.character;

public class NewCharacter {

  private final String name;
  private final Attributes attributes;
  private final CharacterClass characterClass;

  public NewCharacter(String name, Attributes attributes, CharacterClass characterClass) {
    this.name = name;
    this.attributes = attributes;
    this.characterClass = characterClass;
  }

  public String name() {
    return name;
  }

  public Attributes attributes() {
    return attributes;
  }

  public CharacterClass characterClass() {
    return characterClass;
  }
}
