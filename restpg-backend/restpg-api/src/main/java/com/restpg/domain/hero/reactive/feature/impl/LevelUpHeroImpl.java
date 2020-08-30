package com.restpg.domain.hero.reactive.feature.impl;

import com.restpg.domain.account.model.Account;
import com.restpg.domain.hero.exception.HeroNotFoundException;
import com.restpg.domain.hero.model.AccountHero;
import com.restpg.domain.hero.model.AccountHeroCreator;
import com.restpg.domain.hero.reactive.feature.LevelUpHero;
import com.restpg.domain.hero.reactive.repository.HeroRepository;
import com.rpg.model.character.type.Hero;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class LevelUpHeroImpl implements LevelUpHero {

  private final HeroRepository heroRepository;
  private final AccountHeroCreator accountHeroCreator;

  public LevelUpHeroImpl(HeroRepository heroRepository, AccountHeroCreator accountHeroCreator) {
    this.heroRepository = heroRepository;
    this.accountHeroCreator = accountHeroCreator;
  }

  @Override
  public Mono<Hero> handle(UUID heroId, Account account) {
    return heroRepository
        .findByIdAndAccount(heroId, account)
        .switchIfEmpty(Mono.error(HeroNotFoundException::new))
        .doOnNext(Hero::levelUp)
        .map(hero -> accountHeroCreator.create(account, hero))
        .flatMap(heroRepository::update)
        .map(AccountHero::hero);
  }
}
