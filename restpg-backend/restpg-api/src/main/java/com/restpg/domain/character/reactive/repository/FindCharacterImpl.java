package com.restpg.domain.character.reactive.repository;

import com.restpg.domain.account.model.Account;
import com.restpg.domain.character.exception.CharacterNotFoundException;
import com.restpg.domain.character.reactive.feature.FindCharacter;
import com.rpg.model.character.Character;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class FindCharacterImpl implements FindCharacter {

  private final CharacterRepository characterRepository;

  public FindCharacterImpl(CharacterRepository characterRepository) {
    this.characterRepository = characterRepository;
  }

  @Override
  public Mono<Character> handle(UUID characterId, Account account) {
    return characterRepository
        .findByIdAndAccount(characterId, account)
        .switchIfEmpty(Mono.error(new CharacterNotFoundException()));
  }
}
