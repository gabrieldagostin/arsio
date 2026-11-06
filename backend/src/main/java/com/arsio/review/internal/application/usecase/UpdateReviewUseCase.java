package com.arsio.review.internal.application.usecase;

import com.arsio.review.api.exception.ReviewNotFoundException;
import com.arsio.review.internal.application.command.UpdateReviewCommand;
import com.arsio.review.internal.domain.model.Review;
import com.arsio.review.internal.domain.repository.ReviewRepository;
import com.arsio.review.internal.infra.controller.dto.response.AuthorSummary;
import com.arsio.review.internal.infra.controller.dto.response.ReviewResponse;
import com.arsio.review.internal.infra.controller.dto.response.VoteSummary;
import com.arsio.user.api.facade.UserFacade;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UpdateReviewUseCase {

    private final ReviewRepository reviews;
    private final UserFacade userFacade;

    public UpdateReviewUseCase(ReviewRepository reviews, UserFacade userFacade) {
        this.reviews = reviews;
        this.userFacade = userFacade;
    }

    public ReviewResponse execute(UpdateReviewCommand command) {

        Review review = reviews.findById(command.reviewId())
                .orElseThrow(ReviewNotFoundException::new);

        review.update(
                command.rating(),
                command.comment()
        );

        reviews.save(review);

        VoteSummary voteSummary = reviews.findVoteSummary(review.getId());

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
                voteSummary,
                authorSummary
        );
    }
}
