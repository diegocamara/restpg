package com.restpg.infrastructure.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.restpg.infrastructure.configuration.properties.JwtConfigurationPropeprties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import static java.util.Objects.requireNonNull;

@Slf4j
@Configuration
@EnableTransactionManagement
public class RestPGConfiguration {

  private static final String RSA = "RSA";

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public Algorithm algorithm(JwtConfigurationPropeprties jwtConfigurationPropeprties)
      throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
    return Algorithm.RSA256(
        publicKey(jwtConfigurationPropeprties.getPublicKeyFilePath()),
        privateKey(jwtConfigurationPropeprties.getPrivateKeyFilePath()));
  }

  @Bean
  public JWTVerifier jwtVerifier(Algorithm algorithm) {
    return JWT.require(algorithm).build();
  }

  private RSAPrivateKey privateKey(String filePath)
      throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
    final var keySpec = new PKCS8EncodedKeySpec(file(filePath));
    return (RSAPrivateKey) rsaKeyFactory().generatePrivate(keySpec);
  }

  private RSAPublicKey publicKey(String filePath)
      throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
    final var keySpec = new X509EncodedKeySpec(file(filePath));
    return (RSAPublicKey) rsaKeyFactory().generatePublic(keySpec);
  }

  private byte[] file(String filePath) throws IOException {
    try (final var inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
      return requireNonNull(inputStream).readAllBytes();
    }
  }

  private KeyFactory rsaKeyFactory() throws NoSuchAlgorithmException {
    return KeyFactory.getInstance(RSA);
  }
}
