package com.arsio.cart.internal.application.usecase;

import com.arsio.cart.internal.domain.dto.DiscountInfo;
import com.arsio.cart.internal.domain.model.Cart;
import com.arsio.cart.internal.domain.model.CartItem;
import com.arsio.cart.internal.domain.repository.CartRepository;
import com.arsio.cart.internal.domain.valueobject.CartId;
import com.arsio.cart.internal.infra.controller.dto.response.CartResponse;
import com.arsio.game.api.dto.GameSummary;
import com.arsio.game.api.facade.GameFacade;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetCartUseCase {

    private final CartRepository carts;
    private final GameFacade gameFacade;

    public GetCartUseCase(CartRepository carts, GameFacade gameFacade) {
        this.carts = carts;
        this.gameFacade = gameFacade;
    }

    public List<CartResponse> execute(UUID userId) {

        Cart cart = carts.findByUserId(userId)
                .orElse(null);

        if (cart == null || cart.getItems().isEmpty()) {
            return List.of();
        }

        Set<UUID> itemIds = cart.getItems().stream()
                .map(CartItem::getCartId)
                .map(CartId::value)
                .collect(Collectors.toSet());

        Map<UUID, GameSummary> gamesMap = gameFacade.findGamesByIds(itemIds)
                .stream()
                .collect(Collectors.toMap(GameSummary::id, Function.identity()));

        return cart.getItems().stream()
                .map(item -> gamesMap.get(item.getGameId()))
                .filter(Objects::nonNull)
                .map(game -> new CartResponse(
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
