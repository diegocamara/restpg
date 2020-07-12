package com.restpg.infrastructure.web.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpg.exception.RPGException;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Component
@Order(-2)
public class GlobalErrorHandler implements ErrorWebExceptionHandler {

  private final ObjectMapper objectMapper;
  private final Map<Class<?>, Consumer<ServerWebExchange>> rpgExceptionMap;

  public GlobalErrorHandler(
      ObjectMapper objectMapper, Map<Class<?>, Consumer<ServerWebExchange>> rpgExceptionMap) {
    this.objectMapper = objectMapper;
    this.rpgExceptionMap = rpgExceptionMap;
  }

  @Override
  public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {

    serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

    final var dataBufferFactory = serverWebExchange.getResponse().bufferFactory();

    DataBuffer dataBuffer = null;

    if (throwable instanceof RPGException) {
      final var rpgException = (RPGException) throwable;
      rpgExceptionMap.get(rpgException.getClass()).accept(serverWebExchange);
      dataBuffer = dataBuffer(dataBufferFactory, () -> ErrorResponse.from(rpgException));
    } else {
      serverWebExchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
      dataBuffer = dataBuffer(dataBufferFactory, () -> ErrorResponse.from(throwable));
    }

    return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
  }

  private DataBuffer dataBuffer(
      DataBufferFactory dataBufferFactory, Supplier<ErrorResponse> errorResponseSupplier) {
    DataBuffer dataBuffer = null;
    try {
      dataBuffer =
          dataBufferFactory.wrap(objectMapper.writeValueAsBytes(errorResponseSupplier.get()));
    } catch (JsonProcessingException jsonProcessingException) {
      dataBuffer = dataBufferFactory.wrap("".getBytes());
    }
    return dataBuffer;
  }
}
