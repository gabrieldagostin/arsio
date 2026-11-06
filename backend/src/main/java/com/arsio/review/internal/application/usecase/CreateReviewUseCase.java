package com.arsio.review.internal.application.usecase;

import com.arsio.review.internal.application.command.CreateReviewCommand;
import com.arsio.review.internal.domain.model.Review;
import com.arsio.review.internal.domain.repository.ReviewRepository;
import com.arsio.review.internal.infra.controller.dto.response.ReviewResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateReviewUseCase {

    private final ReviewRepository reviews;

    public CreateReviewUseCase(ReviewRepository reviews) {
        this.reviews = reviews;
    }

    public ReviewResponse execute(CreateReviewCommand command) {

        Review review = Review.create(
                command.gameId(),
                command.userId(),
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
