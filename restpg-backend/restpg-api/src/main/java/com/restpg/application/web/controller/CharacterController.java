package com.restpg.application.web.controller;

import com.restpg.application.web.model.request.NewCharacterRequest;
import com.restpg.application.web.model.response.FindCharacterResponse;
import com.restpg.application.web.model.response.NewCharacterResponse;
import com.restpg.domain.account.model.Account;
import com.restpg.domain.character.reactive.feature.CreateCharacter;
import com.restpg.domain.character.reactive.feature.FindCharacter;
import com.rpg.model.character.Type;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/character")
public class CharacterController {

  private final CreateCharacter createCharacter;
  private final FindCharacter findCharacter;

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public Mono<NewCharacterResponse> create(
      @RequestBody NewCharacterRequest newCharacterRequest, Account account) {
    return createCharacter
        .handle(
            Mono.just(newCharacterRequest.toNewCharacter()), account, newCharacterRequest.getType())
        .map(character -> new NewCharacterResponse(character.id()));
  }

  @PostMapping("/hero")
  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  public Mono<NewCharacterResponse> create(
      @RequestBody Mono<NewCharacterRequest> newCharacterRequestMono, Account account) {
    return createCharacter
        .handle(
            newCharacterRequestMono.map(NewCharacterRequest::toNewCharacter), account, Type.HERO)
        .map(character -> new NewCharacterResponse(character.id()));
  }

  @GetMapping("/{characterId}")
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  public Mono<FindCharacterResponse> find(@PathVariable UUID characterId, Account account) {
    return findCharacter.handle(characterId, account).map(FindCharacterResponse::from);
  }
}
