package com.restpg.domain.hero.reactive.feature;

import com.restpg.domain.account.model.Account;
import com.rpg.model.character.type.Hero;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface LevelUpHero {

  Mono<Hero> handle(UUID heroId, Account account);
}
