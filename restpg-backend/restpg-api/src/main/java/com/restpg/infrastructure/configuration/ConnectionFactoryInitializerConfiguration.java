package com.restpg.infrastructure.configuration;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;

@Configuration
public class ConnectionFactoryInitializerConfiguration {

  @Bean
  public ConnectionFactoryInitializer connectionFactoryInitializer(
      ConnectionFactory connectionFactory) {
    final var connectionFactoryInitializer = new ConnectionFactoryInitializer();
    connectionFactoryInitializer.setConnectionFactory(connectionFactory);
    final var resourceDataBasePopulator =
        new ResourceDatabasePopulator(
            new ClassPathResource("schema.sql"), new ClassPathResource("data.sql"));
    connectionFactoryInitializer.setDatabasePopulator(resourceDataBasePopulator);
    return connectionFactoryInitializer;
  }
}
