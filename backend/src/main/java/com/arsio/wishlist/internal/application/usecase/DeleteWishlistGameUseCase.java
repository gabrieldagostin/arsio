package com.arsio.wishlist.internal.application.usecase;

import com.arsio.wishlist.api.exception.WishlistNotFoundException;
import com.arsio.wishlist.internal.application.command.DeleteWishlistGameCommand;
import com.arsio.wishlist.internal.domain.model.Wishlist;
import com.arsio.wishlist.internal.domain.repository.WishlistRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeleteWishlistGameUseCase {

    private final WishlistRepository wishlists;

    public DeleteWishlistGameUseCase(WishlistRepository wishlists) {
        this.wishlists = wishlists;
    }

    public void execute(DeleteWishlistGameCommand command) {

        Wishlist wishlist = wishlists.findByUserId(command.userId())
                .orElseThrow(WishlistNotFoundException::new);

        wishlist.removeGame(command.gameId());

        wishlists.save(wishlist);
    }
}
