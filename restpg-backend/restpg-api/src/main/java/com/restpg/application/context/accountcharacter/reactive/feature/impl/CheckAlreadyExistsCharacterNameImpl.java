package com.restpg.application.context.accountcharacter.reactive.feature.impl;

import com.restpg.application.context.account.model.Account;
import com.restpg.application.context.accountcharacter.reactive.feature.CheckAlreadyExistsCharacterName;
import com.restpg.application.context.accountcharacter.reactive.repository.AccountCharacterRepository;
import reactor.core.publisher.Mono;

public class CheckAlreadyExistsCharacterNameImpl implements CheckAlreadyExistsCharacterName {

  private final AccountCharacterRepository accountCharacterRepository;

  public CheckAlreadyExistsCharacterNameImpl(
      AccountCharacterRepository accountCharacterRepository) {
    this.accountCharacterRepository = accountCharacterRepository;
  }

  @Override
  public Mono<Boolean> handle(Account account, String characterName) {
    return accountCharacterRepository.existsByAccountIdAndCharacterName(
        account.id(), characterName);
  }
}
