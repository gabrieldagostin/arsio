package com.arsio.wishlist.internal.application.usecase;

import com.arsio.wishlist.api.exception.WishlistNotFoundException;
import com.arsio.wishlist.internal.domain.model.Wishlist;
import com.arsio.wishlist.internal.domain.repository.WishlistRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class AddToWishlistUseCase {

    private final WishlistRepository wishlists;

    public AddToWishlistUseCase(WishlistRepository wishlists) {
        this.wishlists = wishlists;
    }

    public void execute(UUID userId, UUID gameId) {

        Wishlist wishlist = wishlists.findByUserId(userId)
                .orElseThrow(WishlistNotFoundException::new);

        wishlist.addGame(gameId);

        wishlists.save(wishlist);
    }
}
