package com.restpg.infrastructure.repository;

import com.restpg.domain.account.encoder.PasswordEncoder;
import com.restpg.domain.account.model.Account;
import com.restpg.domain.account.model.AccountCreator;
import com.restpg.domain.account.model.Role;
import com.restpg.domain.account.reactive.repository.AccountRepository;
import io.r2dbc.spi.Row;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

@AllArgsConstructor
@Repository
public class R2DBCAccountRepository implements AccountRepository {

  private final DatabaseClient databaseClient;
  private final PasswordEncoder passwordEncoder;
  private final AccountCreator accountCreator;

  @Override
  public Mono<Boolean> existsByUsernameOrEmail(String username, String email) {
    return databaseClient
        .execute(
            "SELECT COUNT(*) FROM account "
                + "WHERE UPPER(TRIM(username)) = :username "
                + "OR UPPER(TRIM(email)) = :email")
        .bind("username", username.trim().toUpperCase())
        .bind("email", email.trim().toUpperCase())
        .map(row -> row.get(0, Long.class))
        .first()
        .defaultIfEmpty(0L)
        .map(count -> count > 0);
  }

  @Override
  public Mono<Account> store(Account account) {
    return databaseClient
        .insert()
        .into("account")
        .value("id", account.id())
        .value("username", account.username())
        .value("email", account.email())
        .value("password", passwordEncoder.encode(account.password()))
        .fetch()
        .rowsUpdated()
        .flatMap(
            rowsUpdated ->
                Flux.fromIterable(account.roles())
                    .flatMap(
                        role ->
                            findRoleIdByName(role.name())
                                .flatMap(
                                    roleId ->
                                        databaseClient
                                            .insert()
                                            .into("account_roles")
                                            .value("account_id", account.id())
                                            .value("roles_id", roleId)
                                            .then()))
                    .then(Mono.just(account)));
  }

  private Mono<String> findRoleIdByName(String name) {
    return databaseClient
        .execute("SELECT id FROM roles WHERE name = :name")
        .bind("name", name)
        .map(row -> row.get("id", String.class))
        .one();
  }

  @Override
  public Mono<Account> findByEmail(String email) {
    return databaseClient
        .execute(
            "SELECT account.id, account.username, account.email, account.password, roles.name as role FROM account as account"
                + " INNER JOIN account_roles as account_roles ON account.id = account_roles.account_id"
                + " INNER JOIN roles as roles ON account_roles.roles_id = roles.id"
                + " WHERE UPPER(TRIM(email)) = :email")
        .bind("email", email.trim().toUpperCase())
        .map(row -> row)
        .all()
        .collectList()
        .filter(rows -> rows.size() > 0)
        .map(accountMap());
  }

  @Override
  public Mono<Account> findById(UUID id) {
    return databaseClient
        .execute(
            "SELECT account.id, account.username, account.email, account.password, roles.name as role FROM account as account"
                + " INNER JOIN account_roles as account_roles ON account.id = account_roles.account_id"
                + " INNER JOIN roles as roles ON account_roles.roles_id = roles.id"
                + " WHERE account.id = :id")
        .bind("id", id.toString())
        .map(row -> row)
        .all()
        .collectList()
        .filter(rows -> rows.size() > 0)
        .map(accountMap());
  }

  private Function<List<Row>, Account> accountMap() {
    return rows -> {
      final var firstRow = rows.stream().findFirst().orElseThrow();
      final var roles =
          rows.stream()
              .map(row -> Role.valueOf(row.get("ROLE", String.class)))
              .toArray(Role[]::new);
      return from(firstRow, roles);
    };
  }

  private Account from(Row row, Role... roles) {
    return accountCreator.create(
        UUID.fromString(requireNonNull(row.get("id", String.class))),
        row.get("username", String.class),
        row.get("email", String.class),
        row.get("password", String.class),
        roles);
  }
}
