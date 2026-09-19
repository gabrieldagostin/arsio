package com.arsio.game.internal.infra.Controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.application.command.AssociateGenresCommand;
import com.arsio.game.internal.application.command.DeleteGenreFromGameCommand;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.GenreId;
import com.arsio.game.internal.infra.Controller.dto.request.AssociateGenresRequest;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface GenreControllerMapper {

    AssociateGenresCommand toAssociateGenresCommand(AssociateGenresRequest request);

    DeleteGenreFromGameCommand toDeleteGenreFromGameCommand(UUID gameId, UUID genreId);

    default GameId toGameId(UUID value) {
        return new GameId(value);
    }

    default GenreId toGenreId(UUID value) {
        return new GenreId(value);
    }
}
