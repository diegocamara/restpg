package com.restpg.application.context.account.reactive.feature.impl;

import com.restpg.application.context.account.model.Account;
import com.restpg.application.context.account.model.AccountCreator;
import com.restpg.application.context.account.model.NewAccount;
import com.restpg.application.context.account.reactive.feature.CreateAccount;
import com.restpg.application.context.account.reactive.repository.AccountRepository;
import com.rpg.exception.UsernameOrEmailAlreadyExistsException;
import reactor.core.publisher.Mono;

import static com.rpg.utils.ReactiveUtils.not;

public class CreateAccountImpl implements CreateAccount {

  private final AccountRepository accountRepository;
  private final AccountCreator accountCreator;

  public CreateAccountImpl(AccountRepository accountRepository, AccountCreator accountCreator) {
    this.accountRepository = accountRepository;
    this.accountCreator = accountCreator;
  }

  @Override
  public Mono<Account> handle(NewAccount newAccount) {
    return Mono.just(
            accountCreator.create(newAccount.username(), newAccount.email(), newAccount.password()))
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
}
