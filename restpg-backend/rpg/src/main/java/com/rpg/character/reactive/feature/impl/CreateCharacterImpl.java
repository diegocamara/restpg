package com.rpg.character.reactive.feature.impl;

import com.rpg.character.model.Character;
import com.rpg.character.model.CharacterCreator;
import com.rpg.character.model.NewCharacter;
import com.rpg.character.reactive.feature.CreateCharacter;
import com.rpg.character.reactive.repository.CharacterRepository;
import com.rpg.exception.CharacterNameAlreadyExistsException;
import reactor.core.publisher.Mono;

import static com.rpg.utils.ReactiveUtils.not;

public class CreateCharacterImpl implements CreateCharacter {

  private final CharacterRepository characterRepository;
  private final CharacterCreator characterCreator;

  public CreateCharacterImpl(
      CharacterRepository characterRepository, CharacterCreator characterCreator) {
    this.characterRepository = characterRepository;
    this.characterCreator = characterCreator;
  }

  @Override
  public Mono<Character> handle(NewCharacter newCharacter) {
    return Mono.just(
            characterCreator.create(
                newCharacter.account(), newCharacter.name(), newCharacter.attributes()))
        .filterWhen(
            character ->
                not(
                    characterRepository.existsByAccountAndName(
                        character.account(), character.name())))
        .switchIfEmpty(Mono.error(new CharacterNameAlreadyExistsException()))
        .flatMap(characterRepository::store);
  }
}
