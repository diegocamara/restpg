package com.restpg.application.web.controller;

import com.restpg.application.context.account.reactive.feature.CreateAccount;
import com.restpg.application.web.model.request.SignupRequest;
import com.restpg.application.web.model.response.SignupResponse;
import com.restpg.application.web.service.JwtService;
import com.restpg.infrastructure.web.security.Role;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Collections;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/signup")
public class SignupController {

  private final CreateAccount createAccount;
  private final JwtService jwtService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<SignupResponse> signUp(@RequestBody SignupRequest signupRequest) {
    return createAccount
        .handle(signupRequest.toNewAccount())
        .flatMap(
            account ->
                jwtService
                    .sign(account.id().toString(), Collections.singletonList(Role.ROLE_USER))
                    .map(token -> new SignupResponse(account.username(), token)));
  }
}
