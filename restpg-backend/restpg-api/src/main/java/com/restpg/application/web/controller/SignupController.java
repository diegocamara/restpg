package com.restpg.application.web.controller;

import com.restpg.application.web.model.request.SignupRequest;
import com.restpg.application.web.model.response.SignupResponse;
import com.rpg.account.reactive.feature.CreateAccount;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/signup")
public class SignupController {

  private final CreateAccount createAccount;

  public SignupController(CreateAccount createAccount) {
    this.createAccount = createAccount;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<SignupResponse> signUp(@RequestBody SignupRequest signupRequest) {
    return createAccount.handle(signupRequest.toNewAccount()).map(SignupResponse::new);
  }
}
