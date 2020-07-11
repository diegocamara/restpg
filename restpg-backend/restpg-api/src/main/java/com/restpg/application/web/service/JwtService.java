package com.restpg.application.web.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.restpg.infrastructure.web.security.Role;
import reactor.core.publisher.Mono;

import java.util.List;

public interface JwtService {
  Mono<String> sign(String subject, List<Role> roles);

  Mono<DecodedJWT> verify(String jwt);

  Mono<List<Role>> roles(DecodedJWT decodedJWT);
}
