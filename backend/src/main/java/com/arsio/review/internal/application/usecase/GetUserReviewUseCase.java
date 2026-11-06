package com.arsio.review.internal.application.usecase;

import com.arsio.review.internal.application.command.GetUserReviewCommand;
import com.arsio.review.internal.domain.repository.ReviewRepository;
import com.arsio.review.internal.domain.dto.AuthorSummary;
import com.arsio.review.internal.infra.controller.dto.response.ReviewResponse;
import com.arsio.review.internal.domain.dto.VoteSummary;
import com.arsio.user.api.facade.UserFacade;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GetUserReviewUseCase {

    private final ReviewRepository reviews;
    private final UserFacade userFacade;

    public GetUserReviewUseCase(ReviewRepository reviews, UserFacade userFacade) {
        this.reviews = reviews;
        this.userFacade = userFacade;
    }

    public ReviewResponse execute(GetUserReviewCommand command) {

        return reviews.findByUserIdAndGameId(command.userId(), command.gameId())
                .map(review -> {
                    VoteSummary voteSummary = reviews.findVoteSummary(review.getId());

                    AuthorSummary authorSummary = userFacade.findAuthorInfo(review.getUserId())
                            .map(author -> new AuthorSummary(author.id(), author.username(), author.avatarUrl()))
                            .orElse(null);

                    return new ReviewResponse(
                            review.getId().value(),
                            review.getRating().value(),
                            review.getComment().value(),
                            voteSummary,
                            authorSummary
                    );
                })
                .orElse(null);
    }
}
