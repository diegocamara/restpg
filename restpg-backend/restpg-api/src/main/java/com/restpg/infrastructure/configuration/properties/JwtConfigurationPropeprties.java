package com.restpg.infrastructure.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt-config")
public class JwtConfigurationPropeprties {
  private String publicKeyFilePath;
  private String privateKeyFilePath;
}
