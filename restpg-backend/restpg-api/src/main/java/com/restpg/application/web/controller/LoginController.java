package com.restpg.application.web.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.restpg.application.web.model.request.LoginRequest;
import com.restpg.application.web.model.response.AuthResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/login")
public class LoginController {

  @PostMapping
  public Mono<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
    final var token = JWT.create().sign(Algorithm.HMAC512("testingsecret"));
    return Mono.just(new AuthResponse(token));
  }
}
