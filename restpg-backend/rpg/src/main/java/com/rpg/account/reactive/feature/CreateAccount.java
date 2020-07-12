package com.rpg.account.reactive.feature;

import com.rpg.account.model.Account;
import com.rpg.account.model.NewAccount;
import reactor.core.publisher.Mono;

public interface CreateAccount {
  Mono<Account> handle(NewAccount newAccount);
}
