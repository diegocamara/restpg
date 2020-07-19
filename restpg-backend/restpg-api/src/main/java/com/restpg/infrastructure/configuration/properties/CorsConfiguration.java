package com.restpg.infrastructure.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "cors")
public class CorsConfiguration {
  String[] allowedOrigins;
  String[] allowedMethods;
  String[] allowedHeaders;
}
