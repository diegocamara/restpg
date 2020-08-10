package com.restpg.domain.hero.reactive.feature.impl;

import com.restpg.domain.account.model.Account;
import com.restpg.domain.hero.exception.HeroNameAlreadyExistsException;
import com.restpg.domain.hero.model.AccountHero;
import com.restpg.domain.hero.model.AccountHeroCreator;
import com.restpg.domain.hero.reactive.feature.CreateHero;
import com.restpg.domain.hero.reactive.repository.HeroRepository;
import com.rpg.model.character.NewHero;
import com.rpg.model.character.type.Hero;
import com.rpg.model.character.type.HeroCreator;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

import static com.rpg.utils.ReactiveUtils.not;

public class CreateHeroImpl implements CreateHero {

  private final HeroRepository heroRepository;
  private final HeroCreator heroCreator;
  private final AccountHeroCreator accountHeroCreator;

  public CreateHeroImpl(
      HeroRepository heroRepository,
      HeroCreator heroCreator,
      AccountHeroCreator accountHeroCreator) {
    this.heroRepository = heroRepository;
    this.heroCreator = heroCreator;
    this.accountHeroCreator = accountHeroCreator;
  }

  @Override
  public Mono<Hero> handle(Mono<NewHero> newHeroMono, Account account) {
    return newHeroMono
        .filterWhen(
            newHero ->
                not(
                    heroRepository.existsByAccountIdAndHeroName(
                        account.id(), newHero.biography().name())))
        .switchIfEmpty(Mono.error(new HeroNameAlreadyExistsException()))
        .map(
            newHero ->
                heroCreator.create(
                    newHero.heroClass(),
                    newHero.biography(),
                    newHero.attributes(),
                    BigInteger.ZERO,
                    1))
        .map(hero -> accountHeroCreator.create(account, hero))
        .flatMap(heroRepository::store)
        .map(AccountHero::hero);
  }
}
