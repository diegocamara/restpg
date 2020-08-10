package com.restpg.application.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class NewHeroResponse {
  private UUID id;
}
