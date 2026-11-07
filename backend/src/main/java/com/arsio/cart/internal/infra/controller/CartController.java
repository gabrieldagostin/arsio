package com.arsio.cart.internal.infra.controller;

import com.arsio.cart.internal.application.command.AddItemToCartCommand;
import com.arsio.cart.internal.application.usecase.AddItemToCartUseCase;
import com.arsio.cart.internal.application.usecase.GetCartUseCase;
import com.arsio.cart.internal.infra.controller.dto.request.AddItemToCartRequest;
import com.arsio.cart.internal.infra.controller.dto.response.CartResponse;
import com.arsio.cart.internal.infra.controller.mapper.CartControllerMapper;
import com.arsio.config.security.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartControllerMapper mapper;
    private final GetCartUseCase getCartUseCase;
    private final AddItemToCartUseCase addItemToCartUseCase;

    @GetMapping
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<List<CartResponse>> getCart() {

        List<CartResponse> response = getCartUseCase.execute(SecurityUtils.getCurrentUserId());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/items")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<Void> addItemToCart(
            @RequestBody @Valid AddItemToCartRequest request) {

        AddItemToCartCommand command = mapper.toAddItemToCartCommand(SecurityUtils.getCurrentUserId(), request);

        addItemToCartUseCase.execute(command);

        return ResponseEntity.noContent().build();
    }
}
