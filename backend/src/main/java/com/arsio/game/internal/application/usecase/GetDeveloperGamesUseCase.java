package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.dto.GetDeveloperGamesView;
import com.arsio.game.internal.application.port.output.GameMediaStorage;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.repository.DiscountRepository;
import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.game.internal.infra.Controller.dto.response.DiscountInfo;
import com.arsio.shared.money.Money;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("gameGetDeveloperGamesUseCase")
@Transactional
public class GetDeveloperGamesUseCase {

    private final GameRepository games;
    private final DiscountRepository discounts;
    private final GameMediaRepository gameMediaRepository;
    private final GameMediaStorage gameMediaStorage;

    public GetDeveloperGamesUseCase(GameRepository gameRepository, DiscountRepository discounts, GameMediaRepository gameMediaRepository, GameMediaStorage gameMediaStorage) {
        this.games = gameRepository;
        this.discounts = discounts;
        this.gameMediaRepository = gameMediaRepository;
        this.gameMediaStorage = gameMediaStorage;
    }

    public List<GetDeveloperGamesView> execute(UUID developerId) {

        List<Game> gameList = games.findAllByDeveloperId(developerId);

        return gameList.stream()
                .map(game -> {
                    String thumbnailUrl = gameMediaRepository.findByGameIdAndRoleThumbnail(game.getId())
                            .map(media -> gameMediaStorage.generatePresignedDownloadUrl(media.getObjectKey().value()))
                            .orElse(null);

                    DiscountInfo discountInfo = discounts.findByGameId(game.getId())
                            .map(discount -> new DiscountInfo(
                                    discount.getPercentage().value(),
                                    discount.calculateAmount(game.getBasePrice()).amount(),
                                    discount.getStartDate(),
                                    discount.getEndDate()
                            ))
                            .orElse(null);

                    Money price = discounts.findByGameId(game.getId())
                            .map(discount -> discount.applyTo(game.getBasePrice()))
                            .orElse(game.getBasePrice());

                    return new GetDeveloperGamesView(
                            game.getId().value(),
                            game.getTitle().value(),
                            price.amount(),
                            game.getBasePrice().amount(),
                            thumbnailUrl,
                            discountInfo != null ? discountInfo.percentage() : null,
                            discountInfo != null ? discountInfo.discountedAmount() : null,
                            discountInfo != null ? discountInfo.startDate() : null,
                            discountInfo != null ? discountInfo.endDate() : null
                    );
                })
                .toList();
    }
}
