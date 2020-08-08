package com.restpg.application.web.controller;

import com.restpg.application.context.account.model.Account;
import com.restpg.application.context.accountcharacter.reactive.feature.CheckAlreadyExistsCharacterName;
import com.restpg.application.context.accountcharacter.reactive.feature.CreateAccountCharacter;
import com.restpg.application.web.model.request.NewCharacterRequest;
import com.restpg.application.web.model.response.NewCharacterResponse;
import com.rpg.character.reactive.feature.CreateCharacter;
import com.rpg.exception.CharacterNameAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.rpg.utils.ReactiveUtils.not;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/character")
public class CharacterController {

  private final CreateCharacter createCharacter;
  private final CheckAlreadyExistsCharacterName checkAlreadyExistsCharacterName;
  private final CreateAccountCharacter createAccountCharacter;

  @PostMapping
  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  public Mono<NewCharacterResponse> create(
      @RequestBody Mono<NewCharacterRequest> newCharacterRequest, Account account) {
    return newCharacterRequest
        .filterWhen(
            request -> not(checkAlreadyExistsCharacterName.handle(account, request.getName())))
        .switchIfEmpty(Mono.error(new CharacterNameAlreadyExistsException()))
        .flatMap(request -> createCharacter.handle(request.toNewCharacter()))
        .flatMap(character -> createAccountCharacter.handle(account.id(), character.id()))
        .map(accountCharacter -> new NewCharacterResponse(accountCharacter.characterId()));
  }
}
