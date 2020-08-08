package com.restpg.infrastructure.repository;

import com.restpg.application.context.accountcharacter.model.AccountCharacter;
import com.restpg.application.context.accountcharacter.reactive.repository.AccountCharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
@AllArgsConstructor
public class R2DBCAccountCharacterRepository implements AccountCharacterRepository {

  private final DatabaseClient databaseClient;

  @Override
  public Mono<Boolean> existsByAccountIdAndCharacterName(UUID accountId, String characterName) {
    return databaseClient
        .execute(
            "SELECT COUNT(*) FROM account_characters INNER JOIN characters ON"
                + " account_characters.character_id = characters.id"
                + " WHERE account_characters.account_id = :account_id AND UPPER(TRIM(characters.name)) = :characterName")
        .bind("account_id", accountId)
        .bind("characterName", characterName.trim().toUpperCase())
        .map(row -> row.get(0, Long.class))
        .first()
        .defaultIfEmpty(0L)
        .map(count -> count > 0);
  }

  @Override
  public Mono<AccountCharacter> store(AccountCharacter accountCharacter) {
    return databaseClient
        .insert()
        .into("account_characters")
        .value("account_id", accountCharacter.accountId().toString())
        .value("character_id", accountCharacter.characterId().toString())
        .fetch()
        .rowsUpdated()
        .map(rowsUpdated -> accountCharacter);
  }
}
