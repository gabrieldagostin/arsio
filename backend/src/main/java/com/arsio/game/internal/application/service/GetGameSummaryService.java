package com.arsio.game.internal.application.service;

import com.arsio.game.api.dto.GameSummary;
import com.arsio.game.api.exception.DiscountNotFoundException;
import com.arsio.game.internal.application.port.output.GameMediaStorage;
import com.arsio.game.internal.domain.model.Discount;
import com.arsio.game.internal.domain.repository.DiscountRepository;
import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.shared.money.Money;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class GetGameSummaryService {

    private final GameRepository games;
    private final DiscountRepository discounts;
    private final GameMediaRepository gameMedia;
    private final GameMediaStorage gameMediaStorage;

    public GetGameSummaryService(GameRepository games, DiscountRepository discounts, GameMediaRepository gameMedia, GameMediaStorage gameMediaStorage) {
        this.games = games;
        this.discounts = discounts;
        this.gameMedia = gameMedia;
        this.gameMediaStorage = gameMediaStorage;
    }

    public List<GameSummary> execute(Set<UUID> ids) {

        List<GameId> gameIds = ids
                .stream()
                .map(GameId::new)
                .toList();

        return games.findAllById(gameIds)
                .stream()
                .map(game -> {
                    String thumbnailUrl = gameMedia.findByGameIdAndRoleThumbnail(game.getId())
                            .map(media -> gameMediaStorage.generatePresignedDownloadUrl(media.getObjectKey().value()))
                            .orElse(null);

                    Discount discount = discounts.findByGameId(game.getId())
                            .orElseThrow(DiscountNotFoundException::new);

                    Money discountAmount = discount.calculateAmount(game.getBasePrice());
                    Money price = discount.applyTo(game.getBasePrice());

                    return new GameSummary(
                            game.getId().value(),
                            game.getTitle().value(),
                            price.amount(),
                            game.getBasePrice().amount(),
                            thumbnailUrl,
                            discount.getPercentage().value(),
                            discountAmount.amount(),
                            discount.getStartDate(),
                            discount.getEndDate()
                    );
                })
                .toList();
    }
}
