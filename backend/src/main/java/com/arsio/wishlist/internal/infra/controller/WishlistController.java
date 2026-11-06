package com.arsio.wishlist.internal.infra.controller;

import com.arsio.config.security.SecurityUtils;
import com.arsio.wishlist.internal.application.usecase.AddToWishlistUseCase;
import com.arsio.wishlist.internal.application.usecase.DeleteWishlistGameUseCase;
import com.arsio.wishlist.internal.application.usecase.GetWishlistUseCase;
import com.arsio.wishlist.internal.infra.controller.dto.response.GetWishlistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wishlists")
public class WishlistController {

    private final AddToWishlistUseCase addToWishlistUseCase;
    private final GetWishlistUseCase getWishlistUseCase;
    private final DeleteWishlistGameUseCase deleteWishlistGameUseCase;

    @PostMapping("/games/{gameId}")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<Void> addToWishlist(@PathVariable("gameId") UUID gameId) {

        addToWishlistUseCase.execute(SecurityUtils.getCurrentUserId(), gameId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<List<GetWishlistResponse>> getWishlist() {

        List<GetWishlistResponse> responses = getWishlistUseCase.execute(SecurityUtils.getCurrentUserId());

        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/games/{gameId}")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<Void> deleteGameFromWishlist(@PathVariable("gameId") UUID gameId) {

        deleteWishlistGameUseCase.execute(SecurityUtils.getCurrentUserId(), gameId);

        return ResponseEntity.noContent().build();
    }
}