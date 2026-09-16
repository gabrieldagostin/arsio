package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.domain.repository.GenreRepository;
import com.arsio.game.internal.infra.Controller.dto.response.GenreResponse;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ListGenresUseCase {

    public final GenreRepository genres;

    public ListGenresUseCase(GenreRepository genres) {
        this.genres = genres;
    }

    public PageResult<GenreResponse> execute(String search, Pagination pagination) {

        return genres.findAll(search, pagination)
                .map(genre ->  {
                    return new GenreResponse(
                            genre.getId().value(),
                            genre.getName().value()
                    );
                });
    }
}
