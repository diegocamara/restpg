package com.restpg.application.context.account.reactive.feature;

import com.restpg.application.context.account.model.Account;
import com.restpg.application.context.account.model.NewAccount;
import reactor.core.publisher.Mono;

public interface CreateAccount {
  Mono<Account> handle(NewAccount newAccount);
}
