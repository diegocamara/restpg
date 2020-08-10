package com.restpg.application.web.controller;

import com.restpg.application.web.model.request.SignupRequest;
import com.restpg.application.web.model.response.SignupResponse;
import com.restpg.application.web.service.JwtService;
import com.restpg.domain.account.reactive.feature.CreateAccount;
import com.restpg.infrastructure.web.security.Role;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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
        .handle(signupRequest.toNewAccount(Role.ROLE_USER))
        .flatMap(
            account ->
                jwtService
                    .sign(account.id().toString(), account.roles())
                    .map(token -> new SignupResponse(account.username(), token)));
  }
}
