package com.restpg.application.web.controller;

import com.restpg.application.web.model.request.NewHeroRequest;
import com.restpg.application.web.model.response.FindHeroResponse;
import com.restpg.application.web.model.response.NewHeroResponse;
import com.restpg.domain.account.model.Account;
import com.restpg.domain.hero.reactive.feature.CreateHero;
import com.restpg.domain.hero.reactive.feature.FindHero;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/hero")
public class HeroController {

  private final CreateHero createHero;
  private final FindHero findHero;

  @PostMapping
  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  public Mono<NewHeroResponse> create(
      @RequestBody Mono<NewHeroRequest> newHeroRequestMono, Account account) {
    return createHero
        .handle(newHeroRequestMono.map(NewHeroRequest::toNewHero), account)
        .map(hero -> new NewHeroResponse(hero.id()));
  }

  @GetMapping("/{heroId}")
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  public Mono<FindHeroResponse> find(@PathVariable UUID heroId, Account account) {
    return findHero.handle(heroId, account).map(FindHeroResponse::from);
  }
}
