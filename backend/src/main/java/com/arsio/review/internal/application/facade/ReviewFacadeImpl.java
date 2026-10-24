package com.arsio.review.internal.application.facade;

import com.arsio.review.api.dto.TopRatedGameView;
import com.arsio.review.api.facade.ReviewFacade;
import com.arsio.review.internal.application.usecase.GetGameRatingUseCase;
import com.arsio.shared.pagination.Pagination;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewFacadeImpl implements ReviewFacade {

    private GetGameRatingUseCase getGameRatingUseCase;

    @Override
    public List<TopRatedGameView> findTopRatedGames(Pagination pagination) {
        return getGameRatingUseCase.execute(pagination);
    }
}
