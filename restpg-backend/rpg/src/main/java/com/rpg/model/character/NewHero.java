package com.rpg.model.character;

public class NewHero {

  private final Biography biography;
  private final Attributes attributes;
  private final HeroClass heroClass;

  public NewHero(Biography biography, Attributes attributes, HeroClass heroClass) {
    this.biography = biography;
    this.attributes = attributes;
    this.heroClass = heroClass;
  }

  public Biography biography() {
    return biography;
  }

  public Attributes attributes() {
    return attributes;
  }

  public HeroClass heroClass() {
    return heroClass;
  }
}
