package com.arsio.cart.internal.application.usecase;

import com.arsio.cart.api.exception.CartNotFoundException;
import com.arsio.cart.internal.application.command.RemoveItemFromCartCommand;
import com.arsio.cart.internal.domain.model.Cart;
import com.arsio.cart.internal.domain.repository.CartRepository;
import com.arsio.cart.internal.domain.valueobject.CartStatus;
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

        Cart cart = carts.findByUserIdAndStatus(command.userId(), CartStatus.ACTIVE)
                .orElseThrow(CartNotFoundException::new);

        cart.removeItem(command.gameId());

        carts.save(cart);
    }
}
