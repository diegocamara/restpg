package com.restpg.application.web.controller;

import com.restpg.application.web.model.request.NewCharacterRequest;
import com.restpg.application.web.model.response.NewCharacterResponse;
import com.rpg.account.model.Account;
import com.rpg.character.reactive.feature.CreateCharacter;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/character")
public class CharacterController {

  private final CreateCharacter createCharacter;

  @PostMapping
  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  public Mono<NewCharacterResponse> create(
      @RequestBody NewCharacterRequest newCharacterRequest, Account account) {
    return createCharacter
        .handle(newCharacterRequest.toNewCharacter(account))
        .map(character -> new NewCharacterResponse(character.id()));
  }
}
