package com.rpg.item.model;

public abstract class Item {
  private final String name;

  public Item(String name) {
    this.name = name;
  }

  public String name() {
    return name;
  }
}
