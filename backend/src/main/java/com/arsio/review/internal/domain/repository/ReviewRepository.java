package com.arsio.review.internal.domain.repository;

import com.arsio.review.api.dto.TopRatedGameView;
import com.arsio.shared.pagination.Pagination;

import java.util.List;

public interface ReviewRepository {

    List<TopRatedGameView> findTopRatedGames(Pagination pagination);
}
