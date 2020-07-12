package com.rpg.account.reactive.feature.impl;

import com.rpg.account.model.Account;
import com.rpg.account.model.NewAccount;
import com.rpg.account.reactive.feature.CreateAccount;
import com.rpg.account.reactive.repository.AccountRepository;
import com.rpg.exception.UsernameOrEmailAlreadyExistsException;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public class CreateAccountImpl implements CreateAccount {

  private final AccountRepository accountRepository;

  public CreateAccountImpl(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public Mono<Account> handle(NewAccount newAccount) {
    return Mono.just(
            Account.create(newAccount.username(), newAccount.email(), newAccount.password()))
        .flatMap(
            accountToStore ->
                Mono.just(accountToStore)
                    .filterWhen(
                        account ->
                            not(
                                accountRepository.existsByUsernameOrEmail(
                                    account.username(), account.email())))
                    .switchIfEmpty(Mono.error(new UsernameOrEmailAlreadyExistsException())))
        .flatMap(accountRepository::store);
  }

  private <T extends Publisher<Boolean>> Mono<Boolean> not(T t) {
    return Mono.from(t).map(result -> !result);
  }
}
