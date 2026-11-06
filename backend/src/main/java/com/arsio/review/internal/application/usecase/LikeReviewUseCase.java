package com.arsio.review.internal.application.usecase;

import com.arsio.review.internal.application.command.LikeReviewCommand;
import com.arsio.review.internal.domain.model.ReviewVote;
import com.arsio.review.internal.domain.repository.ReviewVoteRepository;
import com.arsio.review.internal.domain.valueobject.VoteType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LikeReviewUseCase {

    private final ReviewVoteRepository reviewVotes;

    public LikeReviewUseCase(ReviewVoteRepository reviewVotes) {
        this.reviewVotes = reviewVotes;
    }

    public void execute(LikeReviewCommand command) {

        ReviewVote reviewVote = reviewVotes.findById(command.reviewId())
                .orElse(
                        ReviewVote.create(
                                command.reviewId(),
                                command.userId(),
                                VoteType.LIKE
                        ));

        reviewVote.like();

        reviewVotes.save(reviewVote);
    }
}
