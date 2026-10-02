package com.arsio.game.internal.infra.Controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.application.command.ChangeGamePriceCommand;
import com.arsio.game.internal.application.command.CreateGameCommand;
import com.arsio.game.internal.application.command.UpdateGameCommand;
import com.arsio.game.internal.domain.valueobject.Description;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.GameTitle;
import com.arsio.game.internal.infra.Controller.dto.request.ChangeGamePriceRequest;
import com.arsio.game.internal.infra.Controller.dto.request.CreateGameRequest;
import com.arsio.game.internal.infra.Controller.dto.request.UpdateGameRequest;
import com.arsio.shared.money.Money;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface GameControllerMapper {

    CreateGameCommand toCreateGameCommand(CreateGameRequest request);

    UpdateGameCommand toUpdateGameCommand(UUID gameId, UpdateGameRequest request);

    ChangeGamePriceCommand toChangeGamePriceCommand(UUID gameId, ChangeGamePriceRequest request);

    default GameId toGameId(UUID value) {
        return new GameId(value);
    }

    default GameTitle toGameTitle(String value) {
        return new GameTitle(value);
    }

    default Description toDescription(String value) {
        return new Description(value);
    }

    default Money toMoney(BigDecimal value) {
        return new Money(value);
    }
}
