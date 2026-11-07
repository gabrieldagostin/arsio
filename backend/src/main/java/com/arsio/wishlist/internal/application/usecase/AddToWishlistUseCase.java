package com.arsio.wishlist.internal.application.usecase;

import com.arsio.wishlist.internal.application.command.AddToWishlistCommand;
import com.arsio.wishlist.internal.domain.model.Wishlist;
import com.arsio.wishlist.internal.domain.repository.WishlistRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AddToWishlistUseCase {

    private final WishlistRepository wishlists;

    public AddToWishlistUseCase(WishlistRepository wishlists) {
        this.wishlists = wishlists;
    }

    public void execute(AddToWishlistCommand command) {

        Wishlist wishlist = wishlists.findByUserId(command.userId())
                .orElseGet(() -> wishlists.save(Wishlist.createForUser(command.userId())));

        wishlist.addGame(command.gameId());

        wishlists.save(wishlist);
    }
}
