package com.rpg.account.reactive.repository;

import com.rpg.account.model.Account;
import reactor.core.publisher.Mono;

public interface AccountRepository {
  Mono<Boolean> existsByUsernameOrEmail(String username, String email);

  Mono<Account> store(Account account);

  Mono<Account> findByEmail(String email);
}
