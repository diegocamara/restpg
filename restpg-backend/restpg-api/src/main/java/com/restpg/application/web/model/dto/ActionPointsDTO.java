package com.restpg.application.web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionPointsDTO {
  private BigInteger current;
  private BigInteger max;
}
