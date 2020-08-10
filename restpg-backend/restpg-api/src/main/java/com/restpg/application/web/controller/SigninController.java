package com.restpg.application.web.controller;

import com.restpg.application.web.model.request.SigninRequet;
import com.restpg.application.web.model.response.SigninResponse;
import com.restpg.application.web.service.JwtService;
import com.restpg.domain.account.reactive.feature.FindAccount;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/signin")
public class SigninController {

  private final FindAccount findAccount;
  private final JwtService jwtService;

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public Mono<SigninResponse> signin(@RequestBody SigninRequet signinRequet) {
    return findAccount
        .handle(signinRequet.toFindAccountParams())
        .flatMap(
            account ->
                jwtService
                    .sign(account.id().toString(), account.roles())
                    .map(token -> new SigninResponse(account.username(), token)));
  }
}
