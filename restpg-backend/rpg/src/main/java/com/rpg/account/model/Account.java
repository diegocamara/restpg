package com.rpg.account.model;

import java.util.UUID;

public class Account {

  private final UUID id;
  private final String username;
  private final String email;
  private final String password;

  private Account(UUID id, String username, String email, String password) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public static Account create(String username, String email, String password) {
    return new Account(UUID.randomUUID(), username, email, password);
  }

  public UUID id() {
    return id;
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
