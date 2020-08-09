package com.rpg.factory;

public interface AbstractFactory<I, O> {
  O create(I input);
}
