package com.restpg.infrastructure.resolver;

import com.restpg.application.context.account.model.Account;
import com.restpg.application.context.account.reactive.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@AllArgsConstructor
public class AccountArgumentResolver implements HandlerMethodArgumentResolver {

  private final AccountRepository accountRepository;

  @Override
  public boolean supportsParameter(MethodParameter methodParameter) {
    return Account.class.isAssignableFrom(methodParameter.nested().getNestedParameterType());
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
                    .switchIfEmpty(Mono.error(new IllegalArgumentException("account not found"))));
  }
}
