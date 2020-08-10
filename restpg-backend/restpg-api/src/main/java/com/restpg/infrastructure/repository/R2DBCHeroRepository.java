package com.restpg.infrastructure.repository;

import com.restpg.domain.account.model.Account;
import com.restpg.domain.hero.model.AccountHero;
import com.restpg.domain.hero.reactive.repository.HeroRepository;
import com.rpg.model.character.*;
import com.rpg.model.character.type.Hero;
import com.rpg.model.character.type.HeroCreator;
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
public class R2DBCHeroRepository implements HeroRepository {

  private final DatabaseClient databaseClient;
  private final HeroCreator heroCreator;

  @Override
  public Mono<Boolean> existsByAccountIdAndHeroName(UUID accountId, String heroName) {
    return databaseClient
        .execute(
            "SELECT COUNT(*) FROM heroes"
                + " WHERE account_id = :account_id AND UPPER(TRIM(name)) = :heroName")
        .bind("account_id", accountId)
        .bind("heroName", heroName.trim().toUpperCase())
        .map(row -> row.get(0, Long.class))
        .first()
        .defaultIfEmpty(0L)
        .map(count -> count > 0);
  }

  @Override
  public Mono<AccountHero> store(AccountHero accountHero) {

    final var account = accountHero.account();
    final var hero = accountHero.hero();

    return databaseClient
        .insert()
        .into("heroes")
        .value("id", hero.id())
        .value("name", hero.biography().name())
        .value("background", hero.biography().background())
        .value("hero_level", hero.level())
        .value("current_experience", hero.experience().current().longValue())
        .value("next_experience", hero.experience().next().longValue())
        .value("current_health_points", hero.healthPoints().current().longValue())
        .value("max_health_points", hero.healthPoints().max().longValue())
        .value("current_magic_points", hero.magicPoints().current().longValue())
        .value("max_magic_points", hero.magicPoints().max().longValue())
        .value("strength", hero.attributes().strength().longValue())
        .value("constitution", hero.attributes().constitution().longValue())
        .value("dexterity", hero.attributes().dexterity().longValue())
        .value("intelligence", hero.attributes().intelligence().longValue())
        .value("wisdom", hero.attributes().wisdom().longValue())
        .value("charisma", hero.attributes().charisma().longValue())
        .value("gold", hero.gold().longValue())
        .value("hero_class", hero.heroClass().name())
        .value("account_id", account.id().toString())
        .fetch()
        .rowsUpdated()
        .map(rowsUpdated -> accountHero);
  }

  @Override
  public Mono<Hero> findByIdAndAccount(UUID heroId, Account account) {
    return databaseClient
        .execute("SELECT * FROM heroes WHERE id = :heroId AND account_id = :accountId")
        .bind("heroId", heroId.toString())
        .bind("accountId", account.id().toString())
        .map(this::from)
        .one();
  }

  private Hero from(Row row) {

    final var id = UUID.fromString(requireNonNull(row.get("id", String.class)));
    final var name = row.get("name", String.class);
    final var background = row.get("background", String.class);
    final var level = bigIntegerValue(row, "hero_level");
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
    final var heroClass = HeroClass.valueOf(row.get("hero_class", String.class));

    final var biography = new Biography(name, background);
    final var healthPoints = new ActionPoints(currentHealthPoints, maxHealthPoints);
    final var magicPoints = new ActionPoints(currentMagicPoints, maxMagicPoints);
    final var attributes =
        new Attributes(strength, constitution, dexterity, intelligence, wisdom, charisma);
    final var experience = new Experience(currentExperience, nextExperience);
    return heroCreator.create(
        heroClass,
        id,
        biography,
        level.intValue(),
        healthPoints,
        magicPoints,
        attributes,
        experience,
        gold,
        new LinkedList<>(),
        new CharacterEquipment(),
        new LinkedList<>());
  }

  private BigInteger bigIntegerValue(Row row, String name) {
    return requireNonNull(row.get(name, BigDecimal.class)).toBigInteger();
  }
}