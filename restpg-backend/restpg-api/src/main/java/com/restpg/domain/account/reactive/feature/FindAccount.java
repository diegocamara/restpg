package com.restpg.domain.account.reactive.feature;

import com.restpg.domain.account.model.Account;
import com.restpg.domain.account.model.FindAccountParams;
import reactor.core.publisher.Mono;

public interface FindAccount {
  Mono<Account> handle(FindAccountParams findAccountParams);
}
