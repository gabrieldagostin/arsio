package com.arsio.review.api.facade;

import com.arsio.review.api.dto.TopRatedGameView;
import com.arsio.shared.pagination.Pagination;

import java.util.List;

public interface ReviewFacade {

    List<TopRatedGameView> findTopRatedGames(Pagination pagination);
}
