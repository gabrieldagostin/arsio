package com.arsio.review.internal.application.usecase;

import com.arsio.review.api.exception.ReviewNotFoundException;
import com.arsio.review.internal.application.command.UpdateReviewCommand;
import com.arsio.review.internal.domain.model.Review;
import com.arsio.review.internal.domain.repository.ReviewRepository;
import com.arsio.review.internal.infra.controller.dto.response.ReviewResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UpdateReviewUseCase {

    private final ReviewRepository reviews;

    public UpdateReviewUseCase(ReviewRepository reviews) {
        this.reviews = reviews;
    }

    public ReviewResponse execute(UpdateReviewCommand command) {

        Review review = reviews.findById(command.reviewId())
                .orElseThrow(ReviewNotFoundException::new);

        review.update(
                command.rating(),
                command.comment()
        );

        reviews.save(review);

        return new ReviewResponse(
                review.getId().value(),
                review.getRating().value(),
                review.getComment().value()
        );
    }
}
