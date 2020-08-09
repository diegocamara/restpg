package com.restpg.infrastructure.configuration.context;

import com.restpg.domain.character.model.AccountCharacterCreator;
import com.restpg.domain.character.reactive.feature.CreateCharacter;
import com.restpg.domain.character.reactive.feature.FindCharacter;
import com.restpg.domain.character.reactive.feature.impl.CreateCharacterImpl;
import com.restpg.domain.character.reactive.repository.CharacterRepository;
import com.restpg.domain.character.reactive.repository.FindCharacterImpl;
import com.rpg.model.character.CharacterCreator;
import com.rpg.validator.ModelValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CharacterConfiguration {

  @Bean
  public AccountCharacterCreator accountCharacterCreator(ModelValidator modelValidator) {
    return new AccountCharacterCreator(modelValidator);
  }

  @Bean
  public CreateCharacter createCharacter(
      CharacterRepository characterRepository,
      CharacterCreator characterCreator,
      AccountCharacterCreator accountCharacterCreator) {
    return new CreateCharacterImpl(characterRepository, characterCreator, accountCharacterCreator);
  }

  @Bean
  public FindCharacter findCharacter(CharacterRepository characterRepository) {
    return new FindCharacterImpl(characterRepository);
  }

  @Bean
  public CharacterCreator characterCreator(ModelValidator modelValidator) {
    return new CharacterCreator(modelValidator);
  }
}
