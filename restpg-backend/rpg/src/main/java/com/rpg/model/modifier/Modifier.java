package com.rpg.model.modifier;

public abstract class Modifier<T> {

  private final String description;

  public Modifier(String description) {
    this.description = description;
  }

  public String description() {
    return description;
  }

  public abstract void apply(T t);
}
