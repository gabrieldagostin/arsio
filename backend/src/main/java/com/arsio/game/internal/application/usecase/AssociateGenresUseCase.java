package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GameNotFoundException;
import com.arsio.game.api.exception.GenreNotFoundException;
import com.arsio.game.internal.application.command.AssociateGenresCommand;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.model.Genre;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.game.internal.domain.repository.GenreRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.infra.Controller.dto.response.GenreResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssociateGenresUseCase {

    private final GameRepository games;
    private final GenreRepository genres;

    public AssociateGenresUseCase(GameRepository games, GenreRepository genres) {
        this.games = games;
        this.genres = genres;
    }

    public Set<GenreResponse> execute(UUID id, AssociateGenresCommand command) {

        GameId gameId = new GameId(id);

        Game game = games.findById(gameId)
                .orElseThrow(GameNotFoundException::new);

        Set<Genre> genreSet = genres.findAllById(command.genreIds());

        if (genreSet.size() != command.genreIds().size()) {
            throw new GenreNotFoundException();
        }

        game.associateGenres(command.genreIds());

        games.save(game);

        return genreSet.stream()
                .map(genre -> {
                    return new GenreResponse(
                            genre.getId().value(),
                            genre.getName().value()
                    );
                })
                .collect(Collectors.toSet());
    }
}
