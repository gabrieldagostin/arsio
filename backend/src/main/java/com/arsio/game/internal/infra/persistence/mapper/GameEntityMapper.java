package com.arsio.game.internal.infra.persistence.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.valueobject.Description;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.GameTitle;
import com.arsio.game.internal.infra.persistence.entity.GameEntity;
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

    default String gameTitleToString(GameTitle gameTitle) {
        return gameTitle.value();
    }

    default GameTitle stringToGameTitle(String value) {
        return new GameTitle(value);
    }

    default String descriptionToDescription(Description description) {
        if (description == null) return null;
        return description.value();
    }

    default BigDecimal moneyToBigDecimal(Money money) {
        return money == null ? null : money.amount();
    }

    default Money bigDecimalToMoney(BigDecimal value) {

        if (value == null) {
            return null;
        }
        return new Money(value);
    }
}
