package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GenreNotFoundException;
import com.arsio.game.internal.domain.model.Genre;
import com.arsio.game.internal.domain.repository.GenreRepository;
import com.arsio.game.internal.domain.valueobject.GenreId;
import com.arsio.game.internal.infra.Controller.dto.response.GetGenreResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class ActiveGenreUseCase {

    private final GenreRepository genres;

    public ActiveGenreUseCase(GenreRepository genres) {
        this.genres = genres;
    }

    public GetGenreResponse execute(UUID value) {

        GenreId genreId = new GenreId(value);

        Genre genre = genres.findById(genreId)
                .orElseThrow(GenreNotFoundException::new);

        genre.activate();

        genres.save(genre);

        return new GetGenreResponse(
                genre.getId().value(),
                genre.getName().value()
        );
    }
}
