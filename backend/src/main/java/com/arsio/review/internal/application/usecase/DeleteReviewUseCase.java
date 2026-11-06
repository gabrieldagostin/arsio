package com.arsio.review.internal.application.usecase;

import com.arsio.review.api.exception.ReviewNotFoundException;
import com.arsio.review.internal.domain.model.Review;
import com.arsio.review.internal.domain.repository.ReviewRepository;
import com.arsio.review.internal.domain.valueobject.ReviewId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class DeleteReviewUseCase {

    private final ReviewRepository reviews;

    public DeleteReviewUseCase(ReviewRepository reviews) {
        this.reviews = reviews;
    }

    public void execute(UUID value) {

        ReviewId reviewId = new ReviewId(value);

        Review review = reviews.findById(reviewId)
                .orElseThrow(ReviewNotFoundException::new);

        review.deactivate();

        reviews.save(review);
    }
}
