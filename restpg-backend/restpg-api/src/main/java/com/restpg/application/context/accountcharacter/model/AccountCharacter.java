package com.restpg.application.context.accountcharacter.model;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class AccountCharacter {
  @NotNull private final UUID accountId;
  @NotNull private final UUID characterId;

  protected AccountCharacter(UUID accountId, UUID characterId) {
    this.accountId = accountId;
    this.characterId = characterId;
  }

  public UUID accountId() {
    return accountId;
  }

  public UUID characterId() {
    return characterId;
  }
}
