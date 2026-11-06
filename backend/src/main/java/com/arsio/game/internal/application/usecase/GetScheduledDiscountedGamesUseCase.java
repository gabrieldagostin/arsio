package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.dto.GetDiscountedGameView;
import com.arsio.game.api.exception.DiscountNotFoundException;
import com.arsio.game.internal.application.port.output.GameMediaStorage;
import com.arsio.game.internal.domain.model.Discount;
import com.arsio.game.internal.domain.repository.DiscountRepository;
import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.shared.money.Money;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("gameGetScheduledDiscountedGamesUseCase")
@Transactional
public class GetScheduledDiscountedGamesUseCase {

    private final DiscountRepository discounts;
    private final GameRepository games;
    private final GameMediaRepository gameMediaRepository;
    private final GameMediaStorage gameMediaStorage;

    public GetScheduledDiscountedGamesUseCase(DiscountRepository discounts, GameRepository games, GameMediaRepository gameMediaRepository, GameMediaStorage gameMediaStorage) {
        this.discounts = discounts;
        this.games = games;
        this.gameMediaRepository = gameMediaRepository;
        this.gameMediaStorage = gameMediaStorage;
    }

    public List<GetDiscountedGameView> execute(UUID developerId) {
        return games.findAllScheduledDiscounted(developerId)
                .stream()
                .map(game -> {
                    String thumbnailUrl = gameMediaRepository.findByGameIdAndRoleThumbnail(game.getId())
                            .map(media -> gameMediaStorage.generatePresignedDownloadUrl(media.getObjectKey().value()))
                            .orElse(null);

                    Discount discount = discounts.findScheduledByGameId(game.getId())
                            .orElseThrow(DiscountNotFoundException::new);

                    Money discountAmount = discount.calculateAmount(game.getBasePrice());
                    Money price = discount.applyTo(game.getBasePrice());

                    return new GetDiscountedGameView(
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
