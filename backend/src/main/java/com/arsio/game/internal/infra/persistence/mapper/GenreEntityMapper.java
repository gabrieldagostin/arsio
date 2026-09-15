package com.arsio.game.internal.infra.persistence.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.domain.model.Genre;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.GenreId;
import com.arsio.game.internal.domain.valueobject.GenreName;
import com.arsio.game.internal.infra.persistence.entity.GenreEntity;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(config =  CentralMapperConfig.class)
public interface GenreEntityMapper {

    Genre toDomain(GenreEntity genreEntity);

    GenreEntity toEntity(Genre genre);

    default UUID gameIdToUuid(GenreId genreId) {
        return genreId.value();
    }

    default GameId uuidToGameId(UUID value) {
        return new GameId(value);
    }

    default String nameToString(GenreName genreName) {
        return genreName.value();
    }

    default GenreName stringToGenreName(String value) {
        return new GenreName(value);
    }
}
