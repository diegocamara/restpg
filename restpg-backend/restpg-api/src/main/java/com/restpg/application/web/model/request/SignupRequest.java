package com.restpg.application.web.model.request;

import com.restpg.domain.account.model.NewAccount;
import com.restpg.domain.account.model.Role;
import lombok.Data;

@Data
public class SignupRequest {
  private String username;
  private String email;
  private String password;

  public NewAccount toNewAccount(Role... roles) {
    return new NewAccount(username, email, password, roles);
  }
}
