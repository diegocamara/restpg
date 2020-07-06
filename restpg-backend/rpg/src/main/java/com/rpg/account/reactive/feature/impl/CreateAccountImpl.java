package com.rpg.account.reactive.feature.impl;

import com.rpg.account.encoder.PasswordEncoder;
import com.rpg.account.model.Account;
import com.rpg.account.model.NewAccount;
import com.rpg.account.reactive.feature.CreateAccount;
import com.rpg.account.reactive.repository.AccountRepository;
import com.rpg.exception.RPGException;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class CreateAccountImpl implements CreateAccount {

  private final AccountRepository accountRepository;
  private final PasswordEncoder passwordEncoder;

  public CreateAccountImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
    this.accountRepository = accountRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public Mono<UUID> handle(NewAccount newAccount) {

    return Mono.just(
            Account.create(
                newAccount.username(),
                newAccount.email(),
                passwordEncoder.encode(newAccount.password())))
        .flatMap(
            accountToStore ->
                Mono.just(accountToStore)
                    .filterWhen(
                        account ->
                            not(
                                accountRepository.existsByUsernameOrEmail(
                                    account.username(), account.email())))
                    .switchIfEmpty(
                        Mono.error(new RPGException("Username or email already exists"))))
        .flatMap(accountRepository::store);
  }

  private <T extends Publisher<Boolean>> Mono<Boolean> not(T t) {
    return Mono.from(t).map(result -> !result);
  }
}
