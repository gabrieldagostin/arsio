package com.arsio.review.internal.application.usecase;

import com.arsio.review.api.exception.ReviewNotFoundException;
import com.arsio.review.internal.domain.model.Review;
import com.arsio.review.internal.domain.repository.ReviewRepository;
import com.arsio.review.internal.domain.valueobject.ReviewId;
import com.arsio.review.internal.infra.controller.dto.response.ReviewResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class GetReviewUseCase {

    private final ReviewRepository reviews;

    public GetReviewUseCase(ReviewRepository reviews) {
        this.reviews = reviews;
    }

    public ReviewResponse execute(UUID value) {

        ReviewId reviewId = new ReviewId(value);

        Review review = reviews.findById(reviewId)
                .orElseThrow(ReviewNotFoundException::new);

        return new ReviewResponse(
                review.getId().value(),
                review.getRating().value(),
                review.getComment().value()
        );
    }
}
