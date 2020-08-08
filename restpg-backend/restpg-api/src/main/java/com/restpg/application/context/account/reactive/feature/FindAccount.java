package com.restpg.application.context.account.reactive.feature;

import com.restpg.application.context.account.model.Account;
import com.restpg.application.context.account.model.FindAccountParams;
import reactor.core.publisher.Mono;

public interface FindAccount {
  Mono<Account> handle(FindAccountParams findAccountParams);
}
