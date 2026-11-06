package com.arsio.wishlist.internal.application.usecase;

import com.arsio.wishlist.internal.domain.exception.WishlistNotFoundException;
import com.arsio.wishlist.internal.domain.model.Wishlist;
import com.arsio.wishlist.internal.domain.repository.WishlistRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class DeleteWishlistUseCase {

    private final WishlistRepository wishlists;

    public DeleteWishlistUseCase(WishlistRepository wishlists) {
        this.wishlists = wishlists;
    }

    public void execute(UUID userId) {

        Wishlist wishlist = wishlists.getByUserId(userId)
                .orElseThrow(WishlistNotFoundException::new);

        wishlist.removeAll();

        wishlists.save(wishlist);
    }
}
