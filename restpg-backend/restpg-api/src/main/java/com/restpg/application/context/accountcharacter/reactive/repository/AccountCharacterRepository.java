package com.restpg.application.context.accountcharacter.reactive.repository;

import com.restpg.application.context.accountcharacter.model.AccountCharacter;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface AccountCharacterRepository {
  Mono<Boolean> existsByAccountIdAndCharacterName(UUID accountId, String characterName);

  Mono<AccountCharacter> store(AccountCharacter accountCharacter);
}
