package com.arsio.review.internal.application.usecase;

import com.arsio.review.internal.domain.model.Review;
import com.arsio.review.internal.domain.repository.ReviewRepository;
import com.arsio.review.internal.domain.dto.AuthorSummary;
import com.arsio.review.internal.infra.controller.dto.response.ReviewResponse;
import com.arsio.review.internal.domain.dto.VoteSummary;
import com.arsio.user.api.facade.UserFacade;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ListReviewsUseCase {

    private final ReviewRepository reviews;
    private final UserFacade userFacade;

    public ListReviewsUseCase(ReviewRepository reviews, UserFacade userFacade) {
        this.reviews = reviews;
        this.userFacade = userFacade;
    }

    public List<ReviewResponse> execute(UUID gameId) {

        List<Review> reviewList = reviews.findAllByGameId(gameId);

        return reviewList.stream()
                .map(review -> {

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
                })
                .toList();
    }
}
