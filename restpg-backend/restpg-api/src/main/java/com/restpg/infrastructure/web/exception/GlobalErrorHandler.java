package com.restpg.infrastructure.web.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpg.exception.RPGException;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(-2)
public class GlobalErrorHandler implements ErrorWebExceptionHandler {

  private final ObjectMapper objectMapper;

  public GlobalErrorHandler(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {

    final var dataBufferFactory = serverWebExchange.getResponse().bufferFactory();

    if (throwable instanceof RPGException) {
      final var rpgException = (RPGException) throwable;
      serverWebExchange.getResponse().setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY);
      serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
      DataBuffer dataBuffer = null;
      try {
        dataBuffer =
            dataBufferFactory.wrap(
                objectMapper.writeValueAsBytes(ErrorResponse.from(rpgException)));
      } catch (JsonProcessingException jsonProcessingException) {
        dataBuffer = dataBufferFactory.wrap("".getBytes());
      }
      return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

    serverWebExchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
    serverWebExchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
    final var dataBuffer =
        dataBufferFactory.wrap(HttpStatus.INTERNAL_SERVER_ERROR.toString().getBytes());
    return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
  }
}
