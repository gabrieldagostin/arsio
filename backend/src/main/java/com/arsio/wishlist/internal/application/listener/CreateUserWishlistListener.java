package com.arsio.wishlist.internal.application.listener;

import com.arsio.user.api.event.CreatedUserEvent;
import com.arsio.wishlist.internal.domain.model.Wishlist;
import com.arsio.wishlist.internal.domain.repository.WishlistRepository;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CreateUserWishlistListener {

    private final WishlistRepository wishlists;

    public CreateUserWishlistListener(WishlistRepository wishlists) {
        this.wishlists = wishlists;
    }

    @Async
    @ApplicationModuleListener
    public void on(CreatedUserEvent event) {
        wishlists.save(Wishlist.createForUser(event.userId()));
    }
}
