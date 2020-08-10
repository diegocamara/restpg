package com.restpg.infrastructure.web.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.restpg.application.web.service.JwtService;
import com.restpg.domain.account.model.Role;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtServiceImpl implements JwtService {
  public static final String ROLES_CLAIM = "ROLES";
  private final Algorithm algorithm;
  private final JWTVerifier jwtVerifier;

  public JwtServiceImpl(Algorithm algorithm, JWTVerifier jwtVerifier) {
    this.algorithm = algorithm;
    this.jwtVerifier = jwtVerifier;
  }

  @Override
  public Mono<String> sign(String subject, List<Role> roles) {
    return Mono.just(
        JWT.create()
            .withSubject(subject)
            .withClaim(ROLES_CLAIM, roles.stream().map(Role::name).collect(Collectors.toList()))
            .sign(algorithm));
  }

  @Override
  public Mono<DecodedJWT> verify(String jwt) {
    return Mono.just(jwt).map(jwtVerifier::verify);
  }

  @Override
  public Mono<List<Role>> roles(DecodedJWT decodedJWT) {
    return Mono.just(decodedJWT.getClaim(ROLES_CLAIM).asList(Role.class));
  }
}
