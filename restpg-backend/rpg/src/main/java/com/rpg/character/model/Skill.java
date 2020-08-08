package com.rpg.character.model;

public abstract class Skill {

  private final String name;
  private final String description;

  protected Skill(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String name() {
    return name;
  }

  public String description() {
    return description;
  }
}
