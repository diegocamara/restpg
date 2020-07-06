package com.rpg.account.reactive.repository;

import com.rpg.account.model.Account;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface AccountRepository {
  Mono<Boolean> existsByUsernameOrEmail(String username, String email);

  Mono<UUID> store(Account account);
}
