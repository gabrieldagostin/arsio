package com.arsio.review.internal.application.usecase;

import com.arsio.review.api.dto.TopRatedGameView;
import com.arsio.review.internal.domain.repository.ReviewRepository;
import com.arsio.shared.pagination.Pagination;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GetGameRatingUseCase {

    private final ReviewRepository reviews;

    public GetGameRatingUseCase(ReviewRepository reviews) {
        this.reviews = reviews;
    }

    public List<TopRatedGameView> execute(Pagination pagination) {
        return reviews.findTopRatedGames(pagination);
    }
}
