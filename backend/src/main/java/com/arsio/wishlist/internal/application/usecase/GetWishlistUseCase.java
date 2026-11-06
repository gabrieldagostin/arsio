package com.arsio.wishlist.internal.application.usecase;

import com.arsio.game.api.dto.GameSummary;
import com.arsio.game.api.facade.GameFacade;
import com.arsio.wishlist.api.exception.WishlistNotFoundException;
import com.arsio.wishlist.internal.domain.model.Wishlist;
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

        List<UUID> gameIds = wishlist.getGameIds();

        if (gameIds.isEmpty()) {
            return List.of();
        }

        Map<UUID, GameSummary> gamesMap = gameFacade.findGamesByIds(gameIds)
                .stream()
                .collect(Collectors.toMap(GameSummary::id, Function.identity()));

        return gameIds.stream()
                .map(gamesMap::get)
                .filter(Objects::nonNull)
                .map(game -> new GetWishlistResponse(
                        game.id(),
                        game.title(),
                        game.price(),
                        game.thumbnailUrl()
                ))
                .toList();
    }
}
