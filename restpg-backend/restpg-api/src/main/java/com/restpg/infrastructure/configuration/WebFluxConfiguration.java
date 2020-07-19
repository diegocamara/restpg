package com.restpg.infrastructure.configuration;

import com.restpg.infrastructure.configuration.properties.CorsConfiguration;
import com.restpg.infrastructure.resolver.AccountArgumentResolver;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
@EnableWebFlux
@AllArgsConstructor
public class WebFluxConfiguration implements WebFluxConfigurer {

  private final AccountArgumentResolver accountArgumentResolver;
  private final CorsConfiguration corsConfiguration;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowedOrigins(corsConfiguration.getAllowedOrigins())
        .allowedMethods(corsConfiguration.getAllowedMethods())
        .allowedHeaders(corsConfiguration.getAllowedHeaders());
  }

  @Override
  public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
    configurer.addCustomResolver(accountArgumentResolver);
  }
}
