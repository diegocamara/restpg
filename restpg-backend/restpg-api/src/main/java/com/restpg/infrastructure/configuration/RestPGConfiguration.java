package com.restpg.infrastructure.configuration;

import com.rpg.account.reactive.feature.CreateAccount;
import com.rpg.account.reactive.feature.impl.CreateAccountImpl;
import com.rpg.account.reactive.repository.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class RestPGConfiguration {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CreateAccount createAccount(
      AccountRepository accountRepository,
      com.rpg.account.encoder.PasswordEncoder passwordEncoder) {
    return new CreateAccountImpl(accountRepository, passwordEncoder);
  }
}
