package com.rpg.character.model;

public class NewCharacter {
  private final String name;
  private final Attributes attributes;

  public NewCharacter(String name, Attributes attributes) {
    this.name = name;
    this.attributes = attributes;
  }

  public String name() {
    return name;
  }

  public Attributes attributes() {
    return attributes;
  }
}
