package com.arsio.review.internal.infra.persistence.adapter;

import com.arsio.review.api.dto.TopRatedGameView;
import com.arsio.review.internal.domain.repository.ReviewRepository;
import com.arsio.review.internal.infra.persistence.mapper.ReviewEntityMapper;
import com.arsio.review.internal.infra.persistence.repository.SpringDataReviewRepository;
import com.arsio.shared.pagination.Pagination;
import com.arsio.shared.pagination.PaginationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaReviewRepositoryAdapter implements ReviewRepository {

    private final ReviewEntityMapper mapper;
    private final PaginationMapper paginationMapper;
    private final SpringDataReviewRepository reviews;

    @Override
    public List<TopRatedGameView> findTopRatedGames(Pagination pagination) {

        Pageable pageable = paginationMapper.toPageable(pagination);

        return reviews.findTopRatedGames(pageable);
    }
}
