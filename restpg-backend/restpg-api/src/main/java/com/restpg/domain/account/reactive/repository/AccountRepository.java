package com.restpg.domain.account.reactive.repository;

import com.restpg.domain.account.model.Account;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface AccountRepository {
  Mono<Boolean> existsByUsernameOrEmail(String username, String email);

  Mono<Account> store(Account account);

  Mono<Account> findByEmail(String email);

  Mono<Account> findById(UUID id);
}
