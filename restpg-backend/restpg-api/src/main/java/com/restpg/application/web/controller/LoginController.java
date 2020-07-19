package com.restpg.application.web.controller;

import com.restpg.application.web.model.request.LoginRequest;
import com.restpg.application.web.model.response.AuthResponse;
import com.restpg.application.web.service.JwtService;
import com.restpg.infrastructure.web.security.Role;
import com.rpg.account.reactive.feature.FindAccount;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static java.util.Collections.singletonList;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/signin")
public class LoginController {

  private final FindAccount findAccount;
  private final JwtService jwtService;

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public Mono<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
    return findAccount
        .handle(loginRequest.toFindAccountParams())
        .flatMap(account -> jwtService.sign(account.id().toString(), singletonList(Role.ROLE_USER)))
        .map(AuthResponse::new);
  }
}
