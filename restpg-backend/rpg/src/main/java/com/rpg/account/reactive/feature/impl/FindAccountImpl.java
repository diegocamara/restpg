package com.rpg.account.reactive.feature.impl;

import com.rpg.account.encoder.PasswordEncoder;
import com.rpg.account.model.Account;
import com.rpg.account.model.FindAccountParams;
import com.rpg.account.reactive.feature.FindAccount;
import com.rpg.account.reactive.repository.AccountRepository;
import com.rpg.exception.IncorrectEmailOrPasswordException;
import reactor.core.publisher.Mono;

public class FindAccountImpl implements FindAccount {

  private final AccountRepository accountRepository;
  private final PasswordEncoder passwordEncoder;

  public FindAccountImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
    this.accountRepository = accountRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Mono<Account> handle(FindAccountParams findAccountParams) {
    return accountRepository
        .findByEmail(findAccountParams.email())
        .switchIfEmpty(Mono.error(new IncorrectEmailOrPasswordException()))
        .filter(
            account -> passwordEncoder.isValid(findAccountParams.password(), account.password()))
        .switchIfEmpty(Mono.error(new IncorrectEmailOrPasswordException()));
  }
}
