package com.restpg.infrastructure.web.security;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationConverter implements ServerAuthenticationConverter {
  @Override
  public Mono<Authentication> convert(ServerWebExchange serverWebExchange) {
    return Mono.justOrEmpty(serverWebExchange)
        .flatMap(
            sw -> Mono.justOrEmpty(sw.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)))
        .filter(authenticationHeaders -> !authenticationHeaders.isEmpty())
        .map(authenticationHeaders -> authenticationHeaders.get(0))
        .map(authenticationHeader -> authenticationHeader.replace("Bearer ", ""))
        .map(token -> new UsernamePasswordAuthenticationToken(token, token));
  }
}
