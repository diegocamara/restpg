package com.restpg.infrastructure.validator;

import com.rpg.exception.ModelValidationException;
import com.rpg.validator.ModelValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class HibernateModelValidator implements ModelValidator {

  private final Validator validator;

  @Override
  public <T> T validate(T model) throws ModelValidationException {
    Set<ConstraintViolation<T>> constraintViolations = validator.validate(model);

    if (!constraintViolations.isEmpty()) {
      final var messages =
          constraintViolations.stream()
              .map(ConstraintViolation::getMessage)
              .collect(Collectors.toList());
      throw new ModelValidationException(messages);
    }

    return model;
  }
}
