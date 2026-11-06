package com.arsio.review.internal.application.command;

import com.arsio.review.internal.domain.model.ReviewVote;
import com.arsio.review.internal.domain.repository.ReviewVoteRepository;
import com.arsio.review.internal.domain.valueobject.VoteType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DislikeReviewUseCase {

    public ReviewVoteRepository reviewVotes;

    public DislikeReviewUseCase(ReviewVoteRepository reviewVotes) {
        this.reviewVotes = reviewVotes;
    }

    public void execute(DislikeReviewCommand command) {

        ReviewVote reviewVote = reviewVotes.findById(command.reviewId())
                .orElse(
                        ReviewVote.create(
                                command.reviewId(),
                                command.userId(),
                                VoteType.DISLIKE
                        ));

        reviewVote.dislike();

        reviewVotes.save(reviewVote);
    }
}