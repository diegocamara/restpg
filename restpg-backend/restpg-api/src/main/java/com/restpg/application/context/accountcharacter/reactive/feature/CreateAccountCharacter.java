package com.restpg.application.context.accountcharacter.reactive.feature;

import com.restpg.application.context.accountcharacter.model.AccountCharacter;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CreateAccountCharacter {
  Mono<AccountCharacter> handle(UUID accountId, UUID characterId);
}
