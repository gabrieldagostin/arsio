package com.arsio.developer.internal.application.usecase;

import com.arsio.developer.internal.domain.valueobject.DeveloperId;
import com.arsio.developer.internal.infra.controller.dto.response.DiscountInfo;
import com.arsio.developer.internal.infra.controller.dto.response.GetDiscountedGameResponse;
import com.arsio.game.api.facade.GameFacade;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("developerGetScheduledDiscountedGamesUseCase")
@Transactional
public class GetScheduledDiscountedGamesUseCase {

    private final GameFacade gameFacade;

    public GetScheduledDiscountedGamesUseCase(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    public List<GetDiscountedGameResponse> execute(UUID value) {

        DeveloperId developerId = new DeveloperId(value);

        return gameFacade.findScheduledDiscounts(developerId.value())
                .stream()
                .map(discountedGame -> {
                    DiscountInfo discountInfo = (discountedGame.discountedAmount() != null)
                            ? new DiscountInfo(
                            discountedGame.discountedPercentage(),
                            discountedGame.discountedAmount(),
                            discountedGame.startDate(),
                            discountedGame.endDate()
                    )
                            : null;

                    return new GetDiscountedGameResponse(
                            discountedGame.gameId(),
                            discountedGame.title(),
                            discountedGame.price(),
                            discountedGame.basePrice(),
                            discountedGame.thumbnailUrl(),
                            discountInfo
                    );
                })
                .toList();
    }
}
