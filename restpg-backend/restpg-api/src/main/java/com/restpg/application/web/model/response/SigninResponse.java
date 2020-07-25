package com.restpg.application.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SigninResponse {
  private String username;
  private String token;
}
