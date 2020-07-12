package com.restpg.infrastructure.web.exception;

import com.rpg.exception.RPGException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.singletonList;

@Data
@AllArgsConstructor
public class ErrorResponse {
  private List<String> errors;

  public static ErrorResponse from(RPGException rpgException) {
    return new ErrorResponse(rpgException.messages());
  }

  public static ErrorResponse from(Throwable throwable) {
    return new ErrorResponse(new LinkedList<>(singletonList(throwable.getMessage())));
  }
}
