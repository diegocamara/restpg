package com.restpg.infrastructure.repository;

import com.rpg.account.model.Account;
import com.rpg.account.reactive.repository.AccountRepository;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class R2DBCAccountRepository implements AccountRepository {

  private final DatabaseClient databaseClient;

  public R2DBCAccountRepository(DatabaseClient databaseClient) {
    this.databaseClient = databaseClient;
  }

  @Override
  public Mono<Boolean> exists(Account account) {
    return databaseClient
        .execute(
            "SELECT COUNT(*) FROM account "
                + "WHERE UPPER(TRIM(username)) = :username "
                + "AND UPPER(TRIM(email)) = :email")
        .bind("username", account.username().trim().toUpperCase())
        .bind("email", account.email().trim().toUpperCase())
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
}
