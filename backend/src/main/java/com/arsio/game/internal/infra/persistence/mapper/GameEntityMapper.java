package com.arsio.game.internal.infra.persistence.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.valueobject.*;
import com.arsio.game.internal.infra.persistence.entity.GameEntity;
import com.arsio.game.internal.infra.persistence.entity.GenreEntity;
import com.arsio.game.internal.infra.persistence.entity.TagEntity;
import com.arsio.shared.money.Money;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface GameEntityMapper {

    Game toDomain(GameEntity gameEntity);

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

    default GenreEntity genreIdToGenreEntity(GenreId genreId) {
        if (genreId == null) {
            return null;
        }

        GenreEntity entity = new GenreEntity();
        entity.setId(genreId.value());

        return entity;
    }

    default TagEntity tagIdToTagEntity(TagId tagId) {
        if (tagId == null) {
            return null;
        }

        TagEntity entity = new TagEntity();
        entity.setId(tagId.value());

        return entity;
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
}
