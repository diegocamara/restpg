package com.rpg.character.model;

import com.rpg.validator.ModelValidator;

import java.math.BigInteger;

public class CharacterCreator {

  private final ModelValidator modelValidator;

  public CharacterCreator(ModelValidator modelValidator) {
    this.modelValidator = modelValidator;
  }

  public Character create(String name, Attributes attributes) {
    final var initialHealthPoints = BigInteger.valueOf(100);
    final var initialMagicPoints = BigInteger.valueOf(20);
    final var initialLevel = 1;
    //    final var character =
    //        new Character(
    //            UUID.randomUUID(),
    //            name,
    //            initialLevel,
    //            new ActionPoints(initialHealthPoints, initialHealthPoints),
    //            new ActionPoints(initialMagicPoints, initialMagicPoints),
    //            attributes,
    //            new Experience(BigInteger.ZERO, BigInteger.valueOf(10)),
    //            BigInteger.ZERO);
    return modelValidator.validate(null);
  }
}
