package com.arsio.wishlist.internal.infra.persistence.adapter;

import com.arsio.wishlist.internal.domain.model.Wishlist;
import com.arsio.wishlist.internal.domain.repository.WishlistRepository;
import com.arsio.wishlist.internal.infra.persistence.mapper.WishlistEntityMapper;
import com.arsio.wishlist.internal.infra.persistence.repository.SpringDataWishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaWishlistRepositoryAdapter implements WishlistRepository {

    private final WishlistEntityMapper mapper;
    private final SpringDataWishlistRepository wishlists;

    @Override
    public Optional<Wishlist> findByUserId(UUID userId) {
        return wishlists.findByUserId(userId)
                .map(mapper::toDomain);
    }

    @Override
    public Wishlist save(Wishlist wishlist) {
        wishlists.save(mapper.toEntity(wishlist));
        return wishlist;
    }
}
