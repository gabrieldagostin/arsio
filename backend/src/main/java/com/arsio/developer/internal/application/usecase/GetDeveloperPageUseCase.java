package com.arsio.developer.internal.application.usecase;

import com.arsio.developer.api.exception.DeveloperNotFoundException;
import com.arsio.developer.internal.domain.valueobject.DeveloperId;
import com.arsio.developer.internal.infra.controller.dto.response.DiscountInfo;
import com.arsio.developer.internal.infra.controller.dto.response.GetDeveloperGameResponse;
import com.arsio.developer.internal.infra.controller.dto.response.GetDeveloperPageResponse;
import com.arsio.developer.internal.infra.controller.dto.response.GetDeveloperProfileResponse;
import com.arsio.game.api.facade.GameFacade;
import com.arsio.user.api.facade.UserFacade;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GetDeveloperPageUseCase {

    private final UserFacade userFacade;
    private final GameFacade gameFacade;

    public GetDeveloperPageUseCase(UserFacade userFacade, GameFacade gameFacade) {
        this.userFacade = userFacade;
        this.gameFacade = gameFacade;
    }

    public GetDeveloperPageResponse execute(UUID value) {

        DeveloperId developerId = new DeveloperId(value);

        GetDeveloperProfileResponse developerProfile = userFacade.findById(developerId.value())
                .map(profile -> new GetDeveloperProfileResponse(
                        profile.displayName(),
                        profile.bio(),
                        profile.avatarUrl(),
                        profile.bannerUrl(),
                        profile.country()
                ))
                .orElseThrow(DeveloperNotFoundException::new);

        List<GetDeveloperGameResponse> developerGames = gameFacade.findByDeveloperId(developerId.value())
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

        return new GetDeveloperPageResponse(
                developerProfile,
                developerGames
        );
    }
}
