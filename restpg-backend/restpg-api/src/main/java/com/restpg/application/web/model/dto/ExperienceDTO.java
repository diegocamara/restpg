package com.restpg.application.web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceDTO {
  private BigInteger current;
  private BigInteger next;
}
