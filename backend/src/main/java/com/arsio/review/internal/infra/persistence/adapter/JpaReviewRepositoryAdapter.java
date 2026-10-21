package com.arsio.review.internal.infra.persistence.adapter;

import com.arsio.review.internal.domain.repository.ReviewRepository;
import com.arsio.review.internal.infra.persistence.mapper.ReviewEntityMapper;
import com.arsio.review.internal.infra.persistence.repository.SpringDataReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaReviewRepositoryAdapter implements ReviewRepository {

    private final ReviewEntityMapper mapper;
    private final SpringDataReviewRepository reviews;
}
