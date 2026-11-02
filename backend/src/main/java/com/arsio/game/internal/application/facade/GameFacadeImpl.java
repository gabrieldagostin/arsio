package com.arsio.game.internal.application.facade;

import com.arsio.game.api.dto.GetDeveloperGamesView;
import com.arsio.game.api.dto.GetDiscountedGameView;
import com.arsio.game.api.facade.GameFacade;
import com.arsio.game.internal.application.usecase.ApplyGameDiscountUseCase;
import com.arsio.game.internal.application.usecase.GetDeveloperGamesUseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class GameFacadeImpl implements GameFacade {

    private final GetDeveloperGamesUseCase getDeveloperGamesUseCase;
    private final ApplyGameDiscountUseCase applyGameDiscountUseCase;

    public GameFacadeImpl(GetDeveloperGamesUseCase getDeveloperGamesUseCase, ApplyGameDiscountUseCase applyGameDiscountUseCase) {
        this.getDeveloperGamesUseCase = getDeveloperGamesUseCase;
        this.applyGameDiscountUseCase = applyGameDiscountUseCase;
    }

    @Override
    public List<GetDeveloperGamesView> findByDeveloperId(UUID developerId) {
        return getDeveloperGamesUseCase.execute(developerId);
    }

    @Override
    public Optional<GetDiscountedGameView> applyGameDiscount(UUID gameId, BigDecimal percentage, LocalDate startDate, LocalDate endDate) {
        return Optional.ofNullable(applyGameDiscountUseCase.execute(
                gameId,
                percentage,
                startDate,
                endDate
        ));
    }
}
