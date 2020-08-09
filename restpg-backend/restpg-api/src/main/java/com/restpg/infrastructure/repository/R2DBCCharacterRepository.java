package com.restpg.infrastructure.repository;

import com.restpg.domain.account.model.Account;
import com.restpg.domain.character.model.AccountCharacter;
import com.restpg.domain.character.reactive.repository.CharacterRepository;
import com.rpg.model.character.Character;
import com.rpg.model.character.*;
import io.r2dbc.spi.Row;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Repository
@AllArgsConstructor
public class R2DBCCharacterRepository implements CharacterRepository {

  private final DatabaseClient databaseClient;
  private final CharacterCreator characterCreator;

  @Override
  public Mono<Boolean> existsByAccountIdAndCharacterName(UUID accountId, String characterName) {
    return databaseClient
        .execute(
            "SELECT COUNT(*) FROM characters"
                + " WHERE account_id = :account_id AND UPPER(TRIM(name)) = :characterName")
        .bind("account_id", accountId)
        .bind("characterName", characterName.trim().toUpperCase())
        .map(row -> row.get(0, Long.class))
        .first()
        .defaultIfEmpty(0L)
        .map(count -> count > 0);
  }

  @Override
  public Mono<AccountCharacter> store(AccountCharacter accountCharacter) {

    final var account = accountCharacter.account();
    final var character = accountCharacter.character();

    return databaseClient
        .insert()
        .into("characters")
        .value("id", character.id())
        .value("name", character.biography().name())
        .value("background", character.biography().background())
        .value("character_level", character.level())
        .value("current_experience", character.experience().current().longValue())
        .value("next_experience", character.experience().next().longValue())
        .value("current_health_points", character.healthPoints().current().longValue())
        .value("max_health_points", character.healthPoints().max().longValue())
        .value("current_magic_points", character.magicPoints().current().longValue())
        .value("max_magic_points", character.magicPoints().max().longValue())
        .value("strength", character.attributes().strength().longValue())
        .value("constitution", character.attributes().constitution().longValue())
        .value("dexterity", character.attributes().dexterity().longValue())
        .value("intelligence", character.attributes().intelligence().longValue())
        .value("wisdom", character.attributes().wisdom().longValue())
        .value("charisma", character.attributes().charisma().longValue())
        .value("gold", character.gold().longValue())
        .value("type", character.type().name())
        .value("character_class", character.characterClass().name())
        .value("account_id", account.id().toString())
        .fetch()
        .rowsUpdated()
        .map(rowsUpdated -> accountCharacter);
  }

  @Override
  public Mono<Character> findByIdAndAccount(UUID characterId, Account account) {
    return databaseClient
        .execute("SELECT * FROM characters WHERE id = :characterId AND account_id = :accountId")
        .bind("characterId", characterId.toString())
        .bind("accountId", account.id().toString())
        .map(this::from)
        .one();
  }

  private Character from(Row row) {

    final var id = UUID.fromString(requireNonNull(row.get("id", String.class)));
    final var name = row.get("name", String.class);
    final var background = row.get("background", String.class);
    final var level = bigIntegerValue(row, "character_level");
    final var currentExperience = bigIntegerValue(row, "current_experience");
    final var nextExperience = bigIntegerValue(row, "next_experience");
    final var currentHealthPoints = bigIntegerValue(row, "current_health_points");
    final var maxHealthPoints = bigIntegerValue(row, "max_health_points");
    final var currentMagicPoints = bigIntegerValue(row, "current_magic_points");
    final var maxMagicPoints = bigIntegerValue(row, "max_magic_points");
    final var strength = bigIntegerValue(row, "strength");
    final var constitution = bigIntegerValue(row, "constitution");
    final var dexterity = bigIntegerValue(row, "dexterity");
    final var intelligence = bigIntegerValue(row, "intelligence");
    final var wisdom = bigIntegerValue(row, "wisdom");
    final var charisma = bigIntegerValue(row, "charisma");
    final var gold = bigIntegerValue(row, "gold");
    final var type = Type.valueOf(row.get("type", String.class));
    final var characterClass = CharacterClass.valueOf(row.get("character_class", String.class));

    final var biography = new Biography(name, background);
    final var healthPoints = new ActionPoints(currentHealthPoints, maxHealthPoints);
    final var magicPoints = new ActionPoints(currentMagicPoints, maxMagicPoints);
    final var attributes =
        new Attributes(strength, constitution, dexterity, intelligence, wisdom, charisma);
    final var experience = new Experience(currentExperience, nextExperience);
    return characterCreator.create(
        id,
        biography,
        level.intValue(),
        healthPoints,
        magicPoints,
        attributes,
        experience,
        gold,
        type,
        characterClass,
        new LinkedList<>(),
        new CharacterEquipment(),
        new LinkedList<>());
  }

  private BigInteger bigIntegerValue(Row row, String name) {
    return requireNonNull(row.get(name, BigDecimal.class)).toBigInteger();
  }
}
