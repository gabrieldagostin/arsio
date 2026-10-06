package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.domain.repository.GenreRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.infra.Controller.dto.response.GetGenreResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetGameGenresUseCase {

    private final GenreRepository genres;

    public GetGameGenresUseCase(GenreRepository genres) {
        this.genres = genres;
    }

    public Set<GetGenreResponse> execute(UUID value) {

        GameId gameId = new GameId(value);

        return genres.findAllGenresByGameId(gameId)
                .stream()
                .map(genre -> {
                    return new GetGenreResponse(
                            genre.getId().value(),
                            genre.getName().value()
                    );
                })
                .collect(Collectors.toSet());
    }
}
