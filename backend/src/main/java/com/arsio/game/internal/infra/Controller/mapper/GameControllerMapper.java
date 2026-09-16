package com.arsio.game.internal.infra.Controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.application.command.CreateGameCommand;
import com.arsio.game.internal.domain.valueobject.Description;
import com.arsio.game.internal.domain.valueobject.GameTitle;
import com.arsio.game.internal.infra.Controller.dto.request.CreateGameRequest;
import com.arsio.shared.money.Money;
import org.mapstruct.Mapper;

import java.math.BigDecimal;

@Mapper(config = CentralMapperConfig.class)
public interface GameControllerMapper {

    CreateGameCommand toCreateGameCommand(CreateGameRequest request);

    default GameTitle toGameTitle(String title) {
        return new GameTitle(title);
    }

    default Description toDescription(String description) {
        return new Description(description);
    }

    default Money toMoney(BigDecimal basePrice) {
        return new Money(basePrice);
    }
}
