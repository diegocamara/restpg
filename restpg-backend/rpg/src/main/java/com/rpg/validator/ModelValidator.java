package com.rpg.validator;

import com.rpg.exception.ModelValidationException;

public interface ModelValidator {
  <T> T validate(T model) throws ModelValidationException;
}
