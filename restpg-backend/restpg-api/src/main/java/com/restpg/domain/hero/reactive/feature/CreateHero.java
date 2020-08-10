package com.restpg.domain.hero.reactive.feature;

import com.restpg.domain.account.model.Account;
import com.rpg.model.character.NewHero;
import com.rpg.model.character.type.Hero;
import reactor.core.publisher.Mono;

public interface CreateHero {
  Mono<Hero> handle(Mono<NewHero> newHeroMono, Account account);
}
