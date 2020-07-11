package com.rpg.account.reactive.feature;

import com.rpg.account.model.Account;
import com.rpg.account.model.FindAccountParams;
import reactor.core.publisher.Mono;

public interface FindAccount {
  Mono<Account> handle(FindAccountParams findAccountParams);
}
