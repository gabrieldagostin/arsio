package com.arsio.review.internal.application.usecase;

import com.arsio.review.internal.application.command.CreateReviewCommand;
import com.arsio.review.internal.domain.model.Review;
import com.arsio.review.internal.domain.repository.ReviewRepository;
import com.arsio.review.internal.infra.controller.dto.response.ReviewResponse;
import com.arsio.user.api.facade.UserFacade;
import com.arsio.review.internal.domain.dto.AuthorSummary;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateReviewUseCase {

    private final ReviewRepository reviews;
    private final UserFacade userFacade;

    public CreateReviewUseCase(ReviewRepository reviews, UserFacade userFacade) {
        this.reviews = reviews;
        this.userFacade = userFacade;
    }

    public ReviewResponse execute(CreateReviewCommand command) {

        Review review = Review.create(
                command.gameId(),
                command.userId(),
                command.rating(),
                command.comment()
        );

        reviews.save(review);

        AuthorSummary authorSummary = userFacade.findAuthorInfo(review.getUserId())
                .map(summary -> new AuthorSummary(
                        summary.id(),
                        summary.username(),
                        summary.avatarUrl()
                ))
                .orElse(null);

        return new ReviewResponse(
                review.getId().value(),
                review.getRating().value(),
                review.getComment().value(),
                null,
                authorSummary
        );
    }
}
