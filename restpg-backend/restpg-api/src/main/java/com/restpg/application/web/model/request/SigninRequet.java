package com.restpg.application.web.model.request;

import com.rpg.account.model.FindAccountParams;
import lombok.Data;

@Data
public class SigninRequet {
  private String email;
  private String password;

  public FindAccountParams toFindAccountParams() {
    return FindAccountParams.create(email, password);
  }
}
