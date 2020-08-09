package com.restpg.infrastructure.configuration.context;

import com.restpg.domain.account.model.AccountCreator;
import com.restpg.domain.account.reactive.feature.CreateAccount;
import com.restpg.domain.account.reactive.feature.FindAccount;
import com.restpg.domain.account.reactive.feature.impl.CreateAccountImpl;
import com.restpg.domain.account.reactive.feature.impl.FindAccountImpl;
import com.restpg.domain.account.reactive.repository.AccountRepository;
import com.rpg.validator.ModelValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountConfiguration {

  @Bean
  public CreateAccount createAccount(
      AccountRepository accountRepository, AccountCreator accountCreator) {
    return new CreateAccountImpl(accountRepository, accountCreator);
  }

  @Bean
  public FindAccount findAccount(
      AccountRepository accountRepository,
      com.restpg.domain.account.encoder.PasswordEncoder passwordEncoder) {
    return new FindAccountImpl(accountRepository, passwordEncoder);
  }

  @Bean
  public AccountCreator accountCreator(ModelValidator modelValidator) {
    return new AccountCreator(modelValidator);
  }
}
