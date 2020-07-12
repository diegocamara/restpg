package com.rpg.character.reactive.repository;

import com.rpg.account.model.Account;
import com.rpg.character.model.Character;
import reactor.core.publisher.Mono;

public interface CharacterRepository {
  Mono<Boolean> existsByAccountAndName(Account account, String name);

  Mono<Character> store(Character character);
}
