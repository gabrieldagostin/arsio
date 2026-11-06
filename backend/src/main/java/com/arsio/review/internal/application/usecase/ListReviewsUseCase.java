package com.arsio.review.internal.application.usecase;

import com.arsio.review.internal.domain.model.Review;
import com.arsio.review.internal.domain.repository.ReviewRepository;
import com.arsio.review.internal.infra.controller.dto.response.ReviewResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ListReviewsUseCase {

    private final ReviewRepository reviews;

    public ListReviewsUseCase(ReviewRepository reviews) {
        this.reviews = reviews;
    }

    public List<ReviewResponse> execute(UUID gameId) {

        List<Review> reviewList = reviews.findAllByGameId(gameId);

        return reviewList.stream()
                .map(review -> new ReviewResponse(
                        review.getId().value(),
                        review.getRating().value(),
                        review.getComment().value()
                ))
                .toList();
    }
}
