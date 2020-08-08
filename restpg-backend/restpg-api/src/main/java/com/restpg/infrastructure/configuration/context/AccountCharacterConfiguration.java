package com.restpg.infrastructure.configuration.context;

import com.restpg.application.context.accountcharacter.model.AccountCharacterCreator;
import com.restpg.application.context.accountcharacter.reactive.feature.CheckAlreadyExistsCharacterName;
import com.restpg.application.context.accountcharacter.reactive.feature.CreateAccountCharacter;
import com.restpg.application.context.accountcharacter.reactive.feature.impl.CheckAlreadyExistsCharacterNameImpl;
import com.restpg.application.context.accountcharacter.reactive.feature.impl.CreateAccountCharacterImpl;
import com.restpg.application.context.accountcharacter.reactive.repository.AccountCharacterRepository;
import com.rpg.validator.ModelValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountCharacterConfiguration {
  @Bean
  public CreateAccountCharacter createAccountCharacter(
      AccountCharacterCreator accountCharacterCreator,
      AccountCharacterRepository accountCharacterRepository) {
    return new CreateAccountCharacterImpl(accountCharacterCreator, accountCharacterRepository);
  }

  @Bean
  public CheckAlreadyExistsCharacterName checkAlreadyExistsCharacterName(
      AccountCharacterRepository accountCharacterRepository) {
    return new CheckAlreadyExistsCharacterNameImpl(accountCharacterRepository);
  }

  @Bean
  public AccountCharacterCreator accountCharacterCreator(ModelValidator modelValidator) {
    return new AccountCharacterCreator(modelValidator);
  }
}
