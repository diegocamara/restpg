package com.restpg.application.context.accountcharacter.reactive.feature;

import com.restpg.application.context.account.model.Account;
import reactor.core.publisher.Mono;

public interface CheckAlreadyExistsCharacterName {

  Mono<Boolean> handle(Account account, String characterName);
}
