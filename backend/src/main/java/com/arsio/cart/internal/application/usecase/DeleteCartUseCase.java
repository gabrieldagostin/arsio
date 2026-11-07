package com.arsio.cart.internal.application.usecase;

import com.arsio.cart.api.exception.CartNotFoundException;
import com.arsio.cart.internal.domain.model.Cart;
import com.arsio.cart.internal.domain.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class DeleteCartUseCase {

    private final CartRepository carts;

    public DeleteCartUseCase(CartRepository carts) {
        this.carts = carts;
    }

    public void execute(UUID userId) {

        Cart cart = carts.findByUserId(userId)
                .orElseThrow(CartNotFoundException::new);

        cart.removeAll();

        carts.save(cart);
    }
}
