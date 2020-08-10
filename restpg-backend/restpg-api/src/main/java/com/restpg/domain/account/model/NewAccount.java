package com.restpg.domain.account.model;

import com.restpg.infrastructure.web.security.Role;

public class NewAccount {

  private final String username;
  private final String email;
  private final String password;
  private final Role[] roles;

  public NewAccount(String username, String email, String password, Role... roles) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  public String username() {
    return username;
  }

  public String email() {
    return email;
  }

  public String password() {
    return password;
  }

  public Role[] roles() {
    return roles;
  }
}
