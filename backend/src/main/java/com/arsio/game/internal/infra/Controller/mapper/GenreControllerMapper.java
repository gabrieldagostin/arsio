package com.arsio.game.internal.infra.Controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.application.command.AssociateGenresCommand;
import com.arsio.game.internal.application.command.CreateGenreCommand;
import com.arsio.game.internal.application.command.DeleteGenreFromGameCommand;
import com.arsio.game.internal.application.command.UpdateGenreCommand;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.GenreId;
import com.arsio.game.internal.domain.valueobject.GenreName;
import com.arsio.game.internal.infra.Controller.dto.request.AssociateGenresRequest;
import com.arsio.game.internal.infra.Controller.dto.request.CreateGenreRequest;
import com.arsio.game.internal.infra.Controller.dto.request.UpdateGenreRequest;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface GenreControllerMapper {

    AssociateGenresCommand toAssociateGenresCommand(AssociateGenresRequest request);

    DeleteGenreFromGameCommand toDeleteGenreFromGameCommand(UUID gameId, UUID genreId);

    CreateGenreCommand  toCreateGenreCommand(CreateGenreRequest request);

    UpdateGenreCommand toUpdateGenreCommand(UUID genreId, UpdateGenreRequest request);

    default GameId toGameId(UUID value) {
        return new GameId(value);
    }

    default GenreId toGenreId(UUID value) {
        return new GenreId(value);
    }

    default GenreName toGenreName(String value) {
        return new GenreName(value);
    }
}
