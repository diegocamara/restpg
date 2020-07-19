package com.rpg.exception;

import java.util.List;

public class ModelValidationException extends RPGException {

  public ModelValidationException(List<String> messages) {
    super(messages);
  }
}
