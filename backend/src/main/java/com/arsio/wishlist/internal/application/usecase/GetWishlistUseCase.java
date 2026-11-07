package com.arsio.wishlist.internal.application.usecase;

import com.arsio.game.api.dto.GameSummary;
import com.arsio.game.api.facade.GameFacade;
import com.arsio.wishlist.api.exception.WishlistNotFoundException;
import com.arsio.wishlist.internal.domain.dto.DiscountInfo;
import com.arsio.wishlist.internal.domain.model.Wishlist;
import com.arsio.wishlist.internal.domain.model.WishlistGame;
import com.arsio.wishlist.internal.domain.repository.WishlistRepository;
import com.arsio.wishlist.internal.infra.controller.dto.response.GetWishlistResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetWishlistUseCase {

    private final WishlistRepository wishlists;
    private final GameFacade gameFacade;

    public GetWishlistUseCase(WishlistRepository wishlists, GameFacade gameFacade) {
        this.wishlists = wishlists;
        this.gameFacade = gameFacade;
    }

    public List<GetWishlistResponse> execute(UUID userId) {

        Wishlist wishlist = wishlists.findByUserId(userId)
                .orElseThrow(WishlistNotFoundException::new);

        if (wishlist.getGames().isEmpty()) {
            return List.of();
        }

        Set<UUID> gameIds = wishlist.getGames().stream()
                .map(WishlistGame::getGameId)
                .collect(Collectors.toSet());

        Map<UUID, GameSummary> gamesMap = gameFacade.findGamesByIds(gameIds)
                .stream()
                .collect(Collectors.toMap(GameSummary::id, Function.identity()));

        return wishlist.getGames().stream()
                .sorted(Comparator.comparing(WishlistGame::getAddedAt).reversed())
                .map(item -> gamesMap.get(item.getGameId()))
                .filter(Objects::nonNull)
                .map(game -> new GetWishlistResponse(
                        game.id(),
                        game.title(),
                        game.price(),
                        game.basePrice(),
                        game.thumbnailUrl(),
                        new DiscountInfo(
                                game.discountedPercentage(),
                                game.discountedAmount(),
                                game.startDate(),
                                game.endDate()
                        )
                ))
                .toList();
    }
}
