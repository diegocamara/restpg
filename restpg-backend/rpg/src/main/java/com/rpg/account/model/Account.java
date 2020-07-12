package com.rpg.account.model;

import com.rpg.exception.PropertyException;

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
    validateUsername(username);
    validateEmail(email);
    validatePassword(password);
    return new Account(UUID.randomUUID(), username, email, password);
  }

  public static Account create(UUID id, String username, String email, String password) {
    validateId(id);
    validateUsername(username);
    validateEmail(email);
    validatePassword(password);
    return new Account(id, username, email, password);
  }

  private static void validateId(UUID id) {
    if (id == null) {
      throw new PropertyException("invalid id");
    }
  }

  private static void validatePassword(String password) {
    if (password == null || password.isBlank()) {
      throw new PropertyException("invalid password");
    }
  }

  private static void validateEmail(String email) {
    if (email == null || email.isBlank()) {
      throw new PropertyException("invalid email");
    }
  }

  private static void validateUsername(String username) {
    if (username == null || username.isBlank()) {
      throw new PropertyException("invalid username");
    }
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
