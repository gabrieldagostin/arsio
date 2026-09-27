package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.application.command.CreateGenreCommand;
import com.arsio.game.internal.domain.model.Genre;
import com.arsio.game.internal.domain.repository.GenreRepository;
import com.arsio.game.internal.infra.Controller.dto.response.GetGenreResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateGenreUseCase {

    private final GenreRepository genres;

    public CreateGenreUseCase(GenreRepository genres) {
        this.genres = genres;
    }

    public GetGenreResponse execute(CreateGenreCommand command) {

        Genre genre = Genre.create(command.name());

        genres.save(genre);

        return new GetGenreResponse(
                genre.getId().value(),
                genre.getName().value()
        );
    }
}
