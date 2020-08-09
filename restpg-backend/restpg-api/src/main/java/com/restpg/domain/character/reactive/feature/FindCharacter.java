package com.restpg.domain.character.reactive.feature;

import com.restpg.domain.account.model.Account;
import com.rpg.model.character.Character;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface FindCharacter {

  Mono<Character> handle(UUID characterId, Account account);
}
