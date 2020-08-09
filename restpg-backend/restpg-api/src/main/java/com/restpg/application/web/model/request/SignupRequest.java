package com.restpg.application.web.model.request;

import com.restpg.domain.account.model.NewAccount;
import lombok.Data;

@Data
public class SignupRequest {
  private String username;
  private String email;
  private String password;

  public NewAccount toNewAccount() {
    return new NewAccount(username, email, password);
  }
}
