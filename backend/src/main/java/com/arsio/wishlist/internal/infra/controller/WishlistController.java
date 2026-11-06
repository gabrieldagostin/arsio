package com.arsio.wishlist.internal.infra.controller;

import com.arsio.config.security.SecurityUtils;
import com.arsio.wishlist.internal.application.usecase.AddToWishlistUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wishlists")
public class WishlistController {

    private final AddToWishlistUseCase addToWishlistUseCase;

    @PostMapping("/games/{gameId}")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<Void> addToWishlist(@PathVariable("gameId") UUID gameId) {

        addToWishlistUseCase.execute(SecurityUtils.getCurrentUserId(), gameId);

        return ResponseEntity.noContent().build();
    }
}