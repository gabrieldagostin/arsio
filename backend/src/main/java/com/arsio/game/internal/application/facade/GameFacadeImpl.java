package com.arsio.game.internal.application.facade;

import com.arsio.game.api.dto.GameSummary;
import com.arsio.game.api.dto.GetDeveloperGamesView;
import com.arsio.game.api.dto.GetDiscountedGameView;
import com.arsio.game.api.dto.UpdateGameDiscountView;
import com.arsio.game.api.facade.GameFacade;
import com.arsio.game.internal.application.service.GetGameSummaryService;
import com.arsio.game.internal.application.usecase.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class GameFacadeImpl implements GameFacade {

    private final GetDeveloperGamesUseCase getDeveloperGamesUseCase;
    private final ApplyGameDiscountUseCase applyGameDiscountUseCase;
    private final GetDeveloperDiscountedGamesUseCase getDeveloperDiscountedGamesUseCase;
    private final GetScheduledDiscountedGamesUseCase getScheduledDiscountedGameUseCase;
    private final UpdateGameDiscountUseCase updateGameDiscountUseCase;
    private final DeleteGameDiscountUseCase deleteGameDiscountUseCase;
    private final GetGameSummaryService getGameSummaryService;

    public GameFacadeImpl(GetDeveloperGamesUseCase getDeveloperGamesUseCase, ApplyGameDiscountUseCase applyGameDiscountUseCase, GetDeveloperDiscountedGamesUseCase getDeveloperDiscountedGamesUseCase, GetScheduledDiscountedGamesUseCase getScheduledDiscountedGameUseCase, UpdateGameDiscountUseCase updateGameDiscountUseCase, DeleteGameDiscountUseCase deleteGameDiscountUseCase, GetGameSummaryService getGameSummaryService) {
        this.getDeveloperGamesUseCase = getDeveloperGamesUseCase;
        this.applyGameDiscountUseCase = applyGameDiscountUseCase;
        this.getDeveloperDiscountedGamesUseCase = getDeveloperDiscountedGamesUseCase;
        this.getScheduledDiscountedGameUseCase = getScheduledDiscountedGameUseCase;
        this.updateGameDiscountUseCase = updateGameDiscountUseCase;
        this.deleteGameDiscountUseCase = deleteGameDiscountUseCase;
        this.getGameSummaryService = getGameSummaryService;
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

    @Override
    public List<GetDiscountedGameView> findDiscountedByDeveloperId(UUID developerId) {
        return getDeveloperDiscountedGamesUseCase.execute(developerId);
    }

    @Override
    public List<GetDiscountedGameView> findScheduledDiscounts(UUID developerId) {
        return getScheduledDiscountedGameUseCase.execute(developerId);
    }

    @Override
    public Optional<UpdateGameDiscountView> updateGameDiscount(UUID gameId, BigDecimal percentage, LocalDate startDate, LocalDate endDate) {
        return Optional.ofNullable(updateGameDiscountUseCase.execute(
                gameId,
                percentage,
                startDate,
                endDate
        ));
    }

    @Override
    public void deleteDiscount(UUID gameId) {
        deleteGameDiscountUseCase.execute(gameId);
    }

    @Override
    public List<GameSummary> findGamesByIds(Set<UUID> gameIds) {
        return getGameSummaryService.execute(gameIds);
    }
}
