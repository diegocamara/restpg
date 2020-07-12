package com.rpg.account.model;

public class NewAccount {

  private final String username;
  private final String email;
  private final String password;

  private NewAccount(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public static NewAccount create(String username, String email, String password) {
    return new NewAccount(username, email, password);
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
}
