package com.restpg.domain.character.reactive.repository;

import com.restpg.domain.account.model.Account;
import com.restpg.domain.character.model.AccountCharacter;
import com.rpg.model.character.Character;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CharacterRepository {
  Mono<Boolean> existsByAccountIdAndCharacterName(UUID accountId, String characterName);

  Mono<AccountCharacter> store(AccountCharacter accountCharacter);

  Mono<Character> findByIdAndAccount(UUID characterId, Account account);
}
