package com.restpg.infrastructure.web.security;

import com.restpg.application.web.service.JwtService;
import com.restpg.domain.account.model.Role;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationConverter implements ServerAuthenticationConverter {

  private final JwtService jwtService;

  public JwtAuthenticationConverter(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @Override
  public Mono<Authentication> convert(ServerWebExchange serverWebExchange) {
    return Mono.justOrEmpty(serverWebExchange)
        .flatMap(
            sw -> Mono.justOrEmpty(sw.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)))
        .filter(authenticationHeaders -> !authenticationHeaders.isEmpty())
        .map(authenticationHeaders -> authenticationHeaders.get(0))
        .map(authenticationHeader -> authenticationHeader.replace("Bearer ", ""))
        .flatMap(jwtService::verify)
        .onErrorResume(throwable -> Mono.empty())
        .flatMap(
            decodedJWT ->
                jwtService
                    .roles(decodedJWT)
                    .map(
                        roles ->
                            new UsernamePasswordAuthenticationToken(
                                decodedJWT.getSubject(), null, roles(roles))));
  }

  private List<GrantedAuthority> roles(List<Role> roles) {
    return roles.stream()
        .map(role -> new SimpleGrantedAuthority(role.name()))
        .collect(Collectors.toList());
  }
}
