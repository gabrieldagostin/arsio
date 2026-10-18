package com.arsio.game.internal.infra.persistence.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.domain.model.Discount;
import com.arsio.game.internal.domain.valueobject.DiscountId;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.Percentage;
import com.arsio.game.internal.infra.persistence.entity.DiscountEntity;
import com.arsio.game.internal.infra.persistence.entity.GameEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface DiscountEntityMapper {

    @Mapping(target = "id", source = "discountEntity.id")
    @Mapping(target = "gameId", source = "discountEntity.game.id")
    Discount toDomain(DiscountEntity discountEntity);

    @Mapping(target = "id", source = "discountEntity.id")
    @Mapping(target = "game", source = "gameEntity")
    DiscountEntity toEntity(DiscountEntity discountEntity, GameEntity gameEntity);

    default UUID discountIdToUuid(DiscountId discountId) {
        return discountId.value();
    }

    default DiscountId uuidToDiscountId(UUID value) {
        return new DiscountId(value);
    }

    default UUID gameIdToUuid(GameId gameId) {
        return gameId.value();
    }

    default GameId uuidToGameId(UUID value) {
        return new GameId(value);
    }

    default Double percentageToDouble(Percentage percentage) {
        return percentage.value();
    }

    default Percentage doubleToPercentage(Double value) {
        return new Percentage(value);
    }
}
