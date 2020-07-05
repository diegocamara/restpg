package com.restpg.application.web.model.request;

import lombok.Data;

@Data
public class LoginRequest {
  private String username;
  private String password;
}
