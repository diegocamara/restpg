package com.restpg.domain.hero.reactive.repository;

import com.restpg.domain.account.model.Account;
import com.restpg.domain.hero.model.AccountHero;
import com.rpg.model.character.type.Hero;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface HeroRepository {
  Mono<Boolean> existsByAccountIdAndHeroName(UUID accountId, String heroName);

  Mono<AccountHero> store(AccountHero accountHero);

  Mono<AccountHero> update(AccountHero accountHero);

  Mono<Hero> findByIdAndAccount(UUID heroId, Account account);
}
