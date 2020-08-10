package com.restpg.infrastructure.configuration;

import com.restpg.domain.account.exception.IncorrectEmailOrPasswordException;
import com.restpg.domain.account.exception.UsernameOrEmailAlreadyExistsException;
import com.restpg.domain.hero.exception.HeroNameAlreadyExistsException;
import com.restpg.domain.hero.exception.HeroNotFoundException;
import com.rpg.exception.ModelValidationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Configuration
public class ExceptionMapperConfiguration {

  @Bean
  public Map<Class<?>, Consumer<ServerWebExchange>> rpgExceptionMap() {
    return new HashMap<>() {
      {
        put(UsernameOrEmailAlreadyExistsException.class, conflict());
        put(IncorrectEmailOrPasswordException.class, unauthorized());
        put(HeroNameAlreadyExistsException.class, conflict());
        put(ModelValidationException.class, unprocessableEntity());
        put(HeroNotFoundException.class, notFound());
      }
    };
  }

  private Consumer<ServerWebExchange> notFound() {
    return serverWebExchange -> serverWebExchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
  }

  private Consumer<ServerWebExchange> unauthorized() {
    return serverWebExchange ->
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
  }

  private Consumer<ServerWebExchange> conflict() {
    return serverWebExchange -> serverWebExchange.getResponse().setStatusCode(HttpStatus.CONFLICT);
  }

  private Consumer<ServerWebExchange> unprocessableEntity() {
    return serverWebExchange ->
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY);
  }
}
