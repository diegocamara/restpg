package com.restpg.domain.account.reactive.feature;

import com.restpg.domain.account.model.Account;
import com.restpg.domain.account.model.NewAccount;
import reactor.core.publisher.Mono;

public interface CreateAccount {
  Mono<Account> handle(NewAccount newAccount);
}
