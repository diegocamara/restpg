package com.restpg.application.web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributesDTO {
  private BigInteger strength;
  private BigInteger constitution;
  private BigInteger dexterity;
  private BigInteger intelligence;
  private BigInteger wisdom;
  private BigInteger charisma;
}
