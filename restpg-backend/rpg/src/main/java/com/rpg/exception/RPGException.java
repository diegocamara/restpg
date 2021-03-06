package com.rpg.exception;

import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.singletonList;

public class RPGException extends RuntimeException {

  private final List<String> messages;

  public RPGException(String message) {
    super(message);
    this.messages = new LinkedList<>(singletonList(message));
  }

  public RPGException(List<String> messages) {
    super(String.join(", ", messages));
    this.messages = messages;
  }

  public List<String> messages() {
    return this.messages;
  }
}
