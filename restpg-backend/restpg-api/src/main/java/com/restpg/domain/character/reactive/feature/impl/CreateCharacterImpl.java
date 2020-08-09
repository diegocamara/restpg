package com.restpg.domain.character.reactive.feature.impl;

import com.restpg.domain.account.model.Account;
import com.restpg.domain.character.exception.CharacterNameAlreadyExistsException;
import com.restpg.domain.character.model.AccountCharacter;
import com.restpg.domain.character.model.AccountCharacterCreator;
import com.restpg.domain.character.reactive.feature.CreateCharacter;
import com.restpg.domain.character.reactive.repository.CharacterRepository;
import com.rpg.model.character.Character;
import com.rpg.model.character.CharacterCreator;
import com.rpg.model.character.NewCharacter;
import com.rpg.model.character.Type;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

import static com.rpg.utils.ReactiveUtils.not;

public class CreateCharacterImpl implements CreateCharacter {

  private final CharacterRepository characterRepository;
  private final CharacterCreator characterCreator;
  private final AccountCharacterCreator accountCharacterCreator;

  public CreateCharacterImpl(
      CharacterRepository characterRepository,
      CharacterCreator characterCreator,
      AccountCharacterCreator accountCharacterCreator) {
    this.characterRepository = characterRepository;
    this.characterCreator = characterCreator;
    this.accountCharacterCreator = accountCharacterCreator;
  }

  @Override
  public Mono<Character> handle(Mono<NewCharacter> newCharacterMono, Account account, Type type) {
    return newCharacterMono
        .filterWhen(
            newCharacter ->
                not(
                    characterRepository.existsByAccountIdAndCharacterName(
                        account.id(), newCharacter.biography().name())))
        .switchIfEmpty(Mono.error(new CharacterNameAlreadyExistsException()))
        .map(
            newCharacter ->
                characterCreator.create(
                    newCharacter.biography(),
                    newCharacter.attributes(),
                    type,
                    newCharacter.characterClass(),
                    BigInteger.ZERO,
                    1))
        .map(character -> accountCharacterCreator.create(account, character))
        .flatMap(characterRepository::store)
        .map(AccountCharacter::character);
  }
}
