package com.rpg.character.reactive.repository;

import com.rpg.character.model.Character;
import reactor.core.publisher.Mono;

public interface CharacterRepository {

  Mono<Character> store(Character character);
}
