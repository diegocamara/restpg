package com.restpg.infrastructure.configuration;

import com.rpg.exception.IncorrectEmailOrPasswordException;
import com.rpg.exception.UsernameOrEmailAlreadyExistsException;
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
      }
    };
  }

  private Consumer<ServerWebExchange> unauthorized() {
    return serverWebExchange ->
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
  }

  private Consumer<ServerWebExchange> conflict() {
    return serverWebExchange -> serverWebExchange.getResponse().setStatusCode(HttpStatus.CONFLICT);
  }
}
