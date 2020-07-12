package com.restpg.infrastructure.repository;

import com.rpg.account.model.Account;
import com.rpg.character.model.Character;
import com.rpg.character.reactive.repository.CharacterRepository;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class R2DBCCharacterRepository implements CharacterRepository {

  private final DatabaseClient databaseClient;

  public R2DBCCharacterRepository(DatabaseClient databaseClient) {
    this.databaseClient = databaseClient;
  }

  @Override
  public Mono<Boolean> existsByAccountAndName(Account account, String name) {
    return databaseClient
        .execute(
            "SELECT COUNT(*) FROM characters WHERE account_id = :account_id AND UPPER(TRIM(name)) = :name")
        .bind("account_id", account.id().toString())
        .bind("name", name.trim().toUpperCase())
        .map(row -> row.get(0, Long.class))
        .first()
        .defaultIfEmpty(0L)
        .map(count -> count > 0);
  }

  @Override
  public Mono<Character> store(Character character) {
    return databaseClient
        .insert()
        .into("characters")
        .value("id", character.id())
        .value("account_id", character.account().id().toString())
        .value("name", character.name())
        .value("character_level", character.level())
        .value("current_experience", character.experience().current().longValue())
        .value("next_experience", character.experience().next().longValue())
        .value("gold", character.gold().longValue())
        .fetch()
        .rowsUpdated()
        .map(rowsUpdated -> character);
  }
}
