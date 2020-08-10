package com.restpg.infrastructure.configuration.context;

import com.restpg.domain.hero.model.AccountHeroCreator;
import com.restpg.domain.hero.reactive.feature.CreateHero;
import com.restpg.domain.hero.reactive.feature.FindHero;
import com.restpg.domain.hero.reactive.feature.impl.CreateHeroImpl;
import com.restpg.domain.hero.reactive.feature.impl.FindHeroImpl;
import com.restpg.domain.hero.reactive.repository.HeroRepository;
import com.rpg.model.character.type.HeroCreator;
import com.rpg.validator.ModelValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeroConfiguration {

  @Bean
  public AccountHeroCreator accountCharacterCreator(ModelValidator modelValidator) {
    return new AccountHeroCreator(modelValidator);
  }

  @Bean
  public CreateHero createHero(
      HeroRepository heroRepository,
      HeroCreator heroCreator,
      AccountHeroCreator accountHeroCreator) {
    return new CreateHeroImpl(heroRepository, heroCreator, accountHeroCreator);
  }

  @Bean
  public FindHero findCharacter(HeroRepository heroRepository) {
    return new FindHeroImpl(heroRepository);
  }

  @Bean
  public HeroCreator heroCreator(ModelValidator modelValidator) {
    return new HeroCreator(modelValidator);
  }
}
