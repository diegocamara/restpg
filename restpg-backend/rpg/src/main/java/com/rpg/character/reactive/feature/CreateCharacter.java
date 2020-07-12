package com.rpg.character.reactive.feature;

import com.rpg.character.model.Character;
import com.rpg.character.model.NewCharacter;
import reactor.core.publisher.Mono;

public interface CreateCharacter {
  Mono<Character> handle(NewCharacter newCharacter);
}
