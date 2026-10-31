package com.arsio.game.internal.infra.persistence.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.model.GameStatus;
import com.arsio.game.internal.domain.valueobject.*;
import com.arsio.game.internal.infra.persistence.entity.GameEntity;
import com.arsio.game.internal.infra.persistence.entity.GenreEntity;
import com.arsio.game.internal.infra.persistence.entity.TagEntity;
import com.arsio.shared.money.Money;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(config = CentralMapperConfig.class)
public interface GameEntityMapper {

    Game toDomain(GameEntity gameEntity);

    @Mapping(target = "genres", source = "genres", qualifiedByName = "toGenreEntities")
    @Mapping(target = "tags", source = "tags", qualifiedByName = "toTagEntities")
    @Mapping(target = "createdAt", ignore = true)
    GameEntity toEntity(Game game);

    default UUID gameIdToUuid(GameId gameId) {
        return gameId.value();
    }

    default GameId uuidToGameId(UUID value) {
        return new GameId(value);
    }

    default GenreId genreEntityToGenreId(GenreEntity genreEntity) {
        return new GenreId(genreEntity.getId());
    }

    @Named("toGenreEntities")
    default Set<GenreEntity> toGenreEntities(Set<GenreId> genres) {

        if (genres == null || genres.isEmpty()) {
            return new HashSet<>();
        }

        return genres.stream()
                .map(id -> {
                    GenreEntity entity = new GenreEntity();
                    entity.setId(id.value());
                    return entity;
                })
                .collect(Collectors.toSet());
    }

    @Named("toTagEntities")
    default Set<TagEntity> toTagEntities(Set<TagId> tags) {

        if (tags == null || tags.isEmpty()) {
            return new HashSet<>();
        }

        return tags.stream()
                .map(id -> {
                    TagEntity entity = new TagEntity();
                    entity.setId(id.value());
                    return entity;
                })
                .collect(Collectors.toSet());
    }

    default TagId tagEntityToTagId(TagEntity tagEntity) {
        return new TagId(tagEntity.getId());
    }

    default String gameTitleToString(GameTitle gameTitle) {
        return gameTitle.value();
    }

    default GameTitle stringToGameTitle(String value) {
        return new GameTitle(value);
    }

    default String descriptionToString(Description description) {
        return description == null ? null : description.value();
    }

    default Description stringToDescription(String value) {
        return new Description(value);
    }

    default BigDecimal moneyToBigDecimal(Money money) {
        return money == null ? null : money.amount();
    }

    default Money bigDecimalToMoney(BigDecimal value) {
        return value == null ? null : new Money(value);
    }

    default String gameStatusToString(GameStatus gameStatus) {
        return gameStatus.name();
    }

    default GameStatus stringToGameStatus(String value) {
        return GameStatus.valueOf(value);
    }
}
