package com.restpg.application.context.accountcharacter.reactive.feature.impl;

import com.restpg.application.context.accountcharacter.model.AccountCharacter;
import com.restpg.application.context.accountcharacter.model.AccountCharacterCreator;
import com.restpg.application.context.accountcharacter.reactive.feature.CreateAccountCharacter;
import com.restpg.application.context.accountcharacter.reactive.repository.AccountCharacterRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class CreateAccountCharacterImpl implements CreateAccountCharacter {

  private final AccountCharacterCreator accountCharacterCreator;
  private final AccountCharacterRepository accountCharacterRepository;

  public CreateAccountCharacterImpl(
      AccountCharacterCreator accountCharacterCreator,
      AccountCharacterRepository accountCharacterRepository) {
    this.accountCharacterCreator = accountCharacterCreator;
    this.accountCharacterRepository = accountCharacterRepository;
  }

  @Override
  public Mono<AccountCharacter> handle(UUID accountId, UUID characterId) {
    return Mono.just(accountCharacterCreator.create(accountId, characterId))
        .flatMap(accountCharacterRepository::store);
  }
}
