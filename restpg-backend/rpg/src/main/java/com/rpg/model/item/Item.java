package com.rpg.model.item;

public abstract class Item {
  private final String name;
  private final String description;

  public Item(String name, String description) {
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
