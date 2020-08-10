package com.restpg.domain.account.model;

import com.restpg.infrastructure.web.security.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

public class Account {

  @NotNull private final UUID id;

  @NotBlank(message = "username cannot be blank")
  private final String username;

  @NotBlank(message = "email cannot be blank")
  @Email(message = "invalid email format")
  private final String email;

  @Size(min = 8, message = "password must have at least 8 characters")
  private final String password;

  @NotNull private final List<Role> roles;

  protected Account(UUID id, String username, String email, String password, List<Role> roles) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.roles = roles;
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

  public List<Role> roles() {
    return roles;
  }
}
