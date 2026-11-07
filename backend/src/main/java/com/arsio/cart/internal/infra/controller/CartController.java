package com.arsio.cart.internal.infra.controller;

import com.arsio.cart.internal.application.command.AddItemToCartCommand;
import com.arsio.cart.internal.application.command.RemoveItemFromCartCommand;
import com.arsio.cart.internal.application.usecase.AddItemToCartUseCase;
import com.arsio.cart.internal.application.usecase.DeleteCartUseCase;
import com.arsio.cart.internal.application.usecase.GetCartUseCase;
import com.arsio.cart.internal.application.usecase.RemoveItemFromCartUseCase;
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
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartControllerMapper mapper;
    private final GetCartUseCase getCartUseCase;
    private final AddItemToCartUseCase addItemToCartUseCase;
    private final RemoveItemFromCartUseCase removeItemFromCartUseCase;
    private final DeleteCartUseCase deleteCartUseCase;

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

    @DeleteMapping("/items/{gameId}")
    @PreAuthorize("HasRoel('USER')")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable(name = "gameId") UUID gameId) {

        RemoveItemFromCartCommand command = mapper.toRemoveItemFromCartCommand(SecurityUtils.getCurrentUserId(), gameId);

        removeItemFromCartUseCase.execute(command);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @PreAuthorize("HasRoel('USER')")
    public ResponseEntity<Void> deleteCart() {

        deleteCartUseCase.execute(SecurityUtils.getCurrentUserId());

        return ResponseEntity.noContent().build();
    }
}
