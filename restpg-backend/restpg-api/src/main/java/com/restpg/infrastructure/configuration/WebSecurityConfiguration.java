package com.restpg.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfiguration {

  @Autowired private ReactiveAuthenticationManager reactiveAuthenticationManager;
  //  @Autowired private ServerSecurityContextRepository serverSecurityContextRepository;
  @Autowired private ServerAuthenticationConverter serverAuthenticationConverter;

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
    final var authenticationWebFilter = new AuthenticationWebFilter(reactiveAuthenticationManager);

    authenticationWebFilter.setServerAuthenticationConverter(serverAuthenticationConverter);

    return serverHttpSecurity
        .authorizeExchange()
        .pathMatchers("/v1/login", "/v1/signup")
        .permitAll()
        .anyExchange()
        .authenticated()
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(
            (serverWebExchange, authenticationException) ->
                Mono.fromRunnable(
                    () -> serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)))
        .accessDeniedHandler(
            (serverWebExchange, accessDeniedException) ->
                Mono.fromRunnable(
                    () -> serverWebExchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN)))
        .and()
        .csrf()
        .disable()
        .formLogin()
        .disable()
        .httpBasic()
        .disable()
        .logout()
        .disable()
        .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
        //        .securityContextRepository(serverSecurityContextRepository)

        .build();
  }
}
