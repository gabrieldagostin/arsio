package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GenreNotFoundException;
import com.arsio.game.internal.application.command.UpdateGenreCommand;
import com.arsio.game.internal.domain.model.Genre;
import com.arsio.game.internal.domain.repository.GenreRepository;
import com.arsio.game.internal.infra.Controller.dto.response.GetGenreResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UpdateGenreUseCase {

    private final GenreRepository genres;

    public UpdateGenreUseCase(GenreRepository genres) {
        this.genres = genres;
    }

    public GetGenreResponse execute(UpdateGenreCommand command) {

        Genre genre = genres.findById(command.genreId())
                .orElseThrow(GenreNotFoundException::new);

        genre.update(command.name());

        genres.save(genre);

        return new GetGenreResponse(
                genre.getId().value(),
                genre.getName().value()
        );
    }
}
