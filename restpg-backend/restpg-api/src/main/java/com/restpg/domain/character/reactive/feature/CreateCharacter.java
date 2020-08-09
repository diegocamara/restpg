package com.restpg.domain.character.reactive.feature;

import com.restpg.domain.account.model.Account;
import com.rpg.model.character.Character;
import com.rpg.model.character.NewCharacter;
import com.rpg.model.character.Type;
import reactor.core.publisher.Mono;

public interface CreateCharacter {
  Mono<Character> handle(Mono<NewCharacter> newCharacterMono, Account account, Type type);
}
