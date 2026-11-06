package com.arsio.wishlist.internal.infra.persistence.adapter;

import com.arsio.wishlist.internal.domain.repository.WishlistRepository;
import com.arsio.wishlist.internal.infra.persistence.mapper.WishlistEntityMapper;
import com.arsio.wishlist.internal.infra.persistence.repository.SpringDataWishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaWishlistRepositoryAdapter implements WishlistRepository {

    private final WishlistEntityMapper mapper;
    private final SpringDataWishlistRepository wishlists;
}
