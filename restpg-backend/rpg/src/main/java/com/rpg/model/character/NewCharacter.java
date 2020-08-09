package com.rpg.model.character;

public class NewCharacter {

  private final Biography biography;
  private final Attributes attributes;
  private final CharacterClass characterClass;

  public NewCharacter(Biography biography, Attributes attributes, CharacterClass characterClass) {
    this.biography = biography;
    this.attributes = attributes;
    this.characterClass = characterClass;
  }

  public Biography biography() {
    return biography;
  }

  public Attributes attributes() {
    return attributes;
  }

  public CharacterClass characterClass() {
    return characterClass;
  }
}
