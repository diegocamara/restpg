package com.restpg.application.context.account.model;

import static java.util.Objects.requireNonNull;

public class FindAccountParams {
  private final String email;
  private final String password;

  private FindAccountParams(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public static FindAccountParams create(String email, String password) {
    return new FindAccountParams(
        requireNonNull(email, "email cannot be null"),
        requireNonNull(password, "password cannot be null"));
  }

  public String email() {
    return email;
  }

  public String password() {
    return password;
  }
}
