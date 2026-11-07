package com.arsio.cart.internal.application.usecase;

import com.arsio.cart.api.exception.CartNotFoundException;
import com.arsio.cart.internal.application.command.RemoveItemFromCartCommand;
import com.arsio.cart.internal.domain.model.Cart;
import com.arsio.cart.internal.domain.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RemoveItemFromCartUseCase {

    private final CartRepository carts;

    public RemoveItemFromCartUseCase(CartRepository carts) {
        this.carts = carts;
    }

    public void execute(RemoveItemFromCartCommand command) {

        Cart cart = carts.findByUserId(command.userId())
                .orElseThrow(CartNotFoundException::new);

        cart.removeItem(command.gameId());

        carts.save(cart);
    }
}
