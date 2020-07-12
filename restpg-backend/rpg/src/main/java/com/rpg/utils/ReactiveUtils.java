package com.rpg.utils;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public class ReactiveUtils {
  public static <T extends Publisher<Boolean>> Mono<Boolean> not(T t) {
    return Mono.from(t).map(result -> !result);
  }
}
