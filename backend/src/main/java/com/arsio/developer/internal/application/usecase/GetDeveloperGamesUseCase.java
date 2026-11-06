package com.arsio.developer.internal.application.usecase;

import com.arsio.developer.internal.domain.valueobject.DeveloperId;
import com.arsio.developer.internal.infra.controller.dto.response.DiscountInfo;
import com.arsio.developer.internal.infra.controller.dto.response.GetDeveloperGameResponse;
import com.arsio.game.api.facade.GameFacade;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("developerGetDeveloperGamesUseCase")
@Transactional
public class GetDeveloperGamesUseCase {

    private final GameFacade gameFacade;

    public  GetDeveloperGamesUseCase(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    public List<GetDeveloperGameResponse> execute(UUID value) {

        DeveloperId developerId = new DeveloperId(value);

        return gameFacade.findByDeveloperId(developerId.value())
                .stream()
                .map(game -> {
                    DiscountInfo discountInfo = (game.discountedAmount() != null)
                            ? new DiscountInfo(
                            game.discountedPercentage(),
                            game.discountedAmount(),
                            game.startDate(),
                            game.endDate()
                    )
                            : null;

                    return new GetDeveloperGameResponse(
                            game.gameId(),
                            game.title(),
                            game.price(),
                            game.basePrice(),
                            game.thumbnailUrl(),
                            discountInfo
                    );
                })
                .toList();
    }
}
