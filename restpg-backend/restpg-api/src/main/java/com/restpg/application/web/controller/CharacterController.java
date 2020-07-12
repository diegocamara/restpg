package com.restpg.application.web.controller;

import com.restpg.application.web.model.request.NewCharacterRequest;
import com.restpg.application.web.model.response.NewCharacterResponse;
import com.rpg.account.reactive.repository.AccountRepository;
import com.rpg.character.reactive.feature.CreateCharacter;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/character")
public class CharacterController {

  private final CreateCharacter createCharacter;
  private final AccountRepository accountRepository;

  @PostMapping
  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  public Mono<NewCharacterResponse> create(
      @RequestBody NewCharacterRequest newCharacterRequest, Principal principal) {
    return accountRepository
        .findById(UUID.fromString(principal.getName()))
        .flatMap(account -> createCharacter.handle(newCharacterRequest.toNewCharacter(account)))
        .map(character -> new NewCharacterResponse(character.id()));
  }
}
