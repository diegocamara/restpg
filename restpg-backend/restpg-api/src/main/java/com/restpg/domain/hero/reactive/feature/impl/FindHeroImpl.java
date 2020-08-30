package com.restpg.domain.hero.reactive.feature.impl;

import com.restpg.domain.account.model.Account;
import com.restpg.domain.hero.exception.HeroNotFoundException;
import com.restpg.domain.hero.reactive.feature.FindHero;
import com.restpg.domain.hero.reactive.repository.HeroRepository;
import com.rpg.model.character.type.Hero;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class FindHeroImpl implements FindHero {

  private final HeroRepository heroRepository;

  public FindHeroImpl(HeroRepository heroRepository) {
    this.heroRepository = heroRepository;
  }

  @Override
  public Mono<Hero> handle(UUID heroId, Account account) {
    return heroRepository
        .findByIdAndAccount(heroId, account)
        .switchIfEmpty(Mono.error(HeroNotFoundException::new));
  }
}
