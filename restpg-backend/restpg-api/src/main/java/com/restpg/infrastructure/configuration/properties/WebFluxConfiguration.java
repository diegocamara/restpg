package com.restpg.infrastructure.configuration.properties;

import com.rpg.account.model.Account;
import com.rpg.account.reactive.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@AllArgsConstructor
@Configuration
@EnableWebFlux
public class WebFluxConfiguration implements WebFluxConfigurer {

  private final AccountRepository accountRepository;

  @Override
  public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
    configurer.addCustomResolver(
        new HandlerMethodArgumentResolver() {
          @Override
          public boolean supportsParameter(MethodParameter methodParameter) {
            return Account.class.isAssignableFrom(
                methodParameter.nested().getNestedParameterType());
          }

          @Override
          public Mono<Object> resolveArgument(
              MethodParameter methodParameter,
              BindingContext bindingContext,
              ServerWebExchange serverWebExchange) {
            return serverWebExchange
                .getPrincipal()
                .flatMap(
                    principal ->
                        accountRepository
                            .findById(UUID.fromString(principal.getName()))
                            .switchIfEmpty(Mono.error(new IllegalArgumentException())));
          }
        });
  }
}
