package com.arsio.review.internal.infra.persistence.adapter;

import com.arsio.review.internal.domain.model.ReviewVote;
import com.arsio.review.internal.domain.repository.ReviewVoteRepository;
import com.arsio.review.internal.domain.valueobject.ReviewId;
import com.arsio.review.internal.infra.persistence.mapper.ReviewVoteEntityMapper;
import com.arsio.review.internal.infra.persistence.repository.SpringDataReviewVoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaReviewVoteRepositoryAdapter implements ReviewVoteRepository {

    private final ReviewVoteEntityMapper mapper;
    private final SpringDataReviewVoteRepository reviewVotes;

    @Override
    public Optional<ReviewVote> findById(ReviewId reviewId) {
        return reviewVotes.findById(reviewId.value())
                .map(mapper::toDomain);
    }

    @Override
    public void save(ReviewVote reviewVote) {
        reviewVotes.save(mapper.toEntity(reviewVote));
    }
}
