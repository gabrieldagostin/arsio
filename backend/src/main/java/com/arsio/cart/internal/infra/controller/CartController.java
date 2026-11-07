package com.arsio.cart.internal.infra.controller;

import com.arsio.cart.internal.application.usecase.GetCartUseCase;
import com.arsio.cart.internal.infra.controller.dto.response.CartResponse;
import com.arsio.config.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final GetCartUseCase getCartUseCase;

    @GetMapping
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<List<CartResponse>> getCart() {

        List<CartResponse> response = getCartUseCase.execute(SecurityUtils.getCurrentUserId());

        return ResponseEntity.ok(response);
    }
}
