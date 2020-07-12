package com.restpg.application.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class NewCharacterResponse {
  private UUID id;
}
