package com.arsio.cart.internal.application.usecase;

import com.arsio.cart.internal.application.command.AddItemToCartCommand;
import com.arsio.cart.internal.domain.model.Cart;
import com.arsio.cart.internal.domain.repository.CartRepository;
import com.arsio.cart.internal.domain.valueobject.CartStatus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class AddItemToCartUseCase {

    private final CartRepository carts;

    public AddItemToCartUseCase(CartRepository carts) {
        this.carts = carts;
    }

    public void execute(AddItemToCartCommand command) {

        Cart cart = carts.findByUserId(command.userId())
                .orElseGet(() -> carts.save(Cart.create(
                        command.userId(),
                        CartStatus.ACTIVE
                )));

        cart.addGame(command.gameId(), cart.getId());

        carts.save(cart);
    }
}
