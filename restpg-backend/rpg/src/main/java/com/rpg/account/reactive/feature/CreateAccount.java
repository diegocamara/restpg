package com.rpg.account.reactive.feature;

import com.rpg.account.model.NewAccount;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CreateAccount {
  Mono<UUID> handle(NewAccount newAccount);
}
