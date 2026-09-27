package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GenreNotFoundException;
import com.arsio.game.internal.domain.model.Genre;
import com.arsio.game.internal.domain.repository.GenreRepository;
import com.arsio.game.internal.domain.valueobject.GenreId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class DeleteGenreUseCase {

    private final GenreRepository genres;

    public DeleteGenreUseCase(GenreRepository genres) {
        this.genres = genres;
    }

    public void execute(UUID value) {

        GenreId genreId = new GenreId(value);

        Genre genre = genres.findById(genreId)
                .orElseThrow(GenreNotFoundException::new);

        genre.deactivate();

        genres.save(genre);
    }
}
