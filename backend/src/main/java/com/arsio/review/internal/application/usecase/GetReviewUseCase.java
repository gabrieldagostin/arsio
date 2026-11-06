package com.arsio.review.internal.application.usecase;

import com.arsio.review.api.exception.ReviewNotFoundException;
import com.arsio.review.internal.domain.model.Review;
import com.arsio.review.internal.domain.repository.ReviewRepository;
import com.arsio.review.internal.domain.valueobject.ReviewId;
import com.arsio.review.internal.domain.dto.AuthorSummary;
import com.arsio.review.internal.infra.controller.dto.response.ReviewResponse;
import com.arsio.review.internal.domain.dto.VoteSummary;
import com.arsio.user.api.facade.UserFacade;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class GetReviewUseCase {

    private final ReviewRepository reviews;
    private final UserFacade userFacade;

    public GetReviewUseCase(ReviewRepository reviews, UserFacade userFacade) {
        this.reviews = reviews;
        this.userFacade = userFacade;
    }

    public ReviewResponse execute(UUID value) {

        ReviewId reviewId = new ReviewId(value);

        Review review = reviews.findById(reviewId)
                .orElseThrow(ReviewNotFoundException::new);

        VoteSummary voteSummary = reviews.findVoteSummary(reviewId);

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
