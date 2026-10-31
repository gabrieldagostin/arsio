package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.domain.repository.GenreRepository;
import com.arsio.game.internal.infra.Controller.dto.response.GetGenreResponse;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ListInactiveGenresUseCase {

    private final GenreRepository genres;

    public ListInactiveGenresUseCase(GenreRepository genres) {
        this.genres = genres;
    }

    public PageResult<GetGenreResponse> execute(String search, Pagination pagination) {

        return genres.findAllByActiveFalse(search, pagination)
                .map(genre -> new GetGenreResponse(
                        genre.getId().value(),
                        genre.getName().value()
                ));
    }
}
