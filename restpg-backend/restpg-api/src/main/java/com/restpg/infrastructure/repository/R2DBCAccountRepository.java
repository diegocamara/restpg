package com.restpg.infrastructure.repository;

import com.rpg.account.model.Account;
import com.rpg.account.reactive.repository.AccountRepository;
import io.r2dbc.spi.Row;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Repository
public class R2DBCAccountRepository implements AccountRepository {

  private final DatabaseClient databaseClient;

  public R2DBCAccountRepository(DatabaseClient databaseClient) {
    this.databaseClient = databaseClient;
  }

  @Override
  public Mono<Boolean> existsByUsernameOrEmail(String username, String email) {
    return databaseClient
        .execute(
            "SELECT COUNT(*) FROM account "
                + "WHERE UPPER(TRIM(username)) = :username "
                + "OR UPPER(TRIM(email)) = :email")
        .bind("username", username.trim().toUpperCase())
        .bind("email", email.trim().toUpperCase())
        .map((row, rowMetadata) -> row.get(0, Long.class))
        .first()
        .defaultIfEmpty(0L)
        .map(count -> count > 0);
  }

  @Override
  public Mono<UUID> store(Account account) {
    return databaseClient
        .insert()
        .into("account")
        .value("id", account.id())
        .value("username", account.username())
        .value("email", account.email())
        .value("password", account.password())
        .fetch()
        .rowsUpdated()
        .map(rowsUpdated -> account.id());
  }

  @Override
  public Mono<Account> findByEmail(String email) {
    return databaseClient
        .execute("SELECT * FROM account WHERE UPPER(TRIM(email)) = :email")
        .bind("email", email.trim().toUpperCase())
        .map(this::from)
        .one();
  }

  private Account from(Row row) {
    return Account.create(
        UUID.fromString(requireNonNull(row.get("id", String.class))),
        row.get("username", String.class),
        row.get("email", String.class),
        row.get("password", String.class));
  }
}
