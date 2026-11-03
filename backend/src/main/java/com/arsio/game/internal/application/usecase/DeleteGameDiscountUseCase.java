package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.DiscountNotFoundException;
import com.arsio.game.internal.domain.model.Discount;
import com.arsio.game.internal.domain.repository.DiscountRepository;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class DeleteGameDiscountUseCase {

    private final DiscountRepository discounts;

    public DeleteGameDiscountUseCase(DiscountRepository discounts) {
        this.discounts = discounts;
    }

    public void execute(UUID value) {

        GameId gameId = new GameId(value);

        Discount discount = discounts.findByGameId(gameId)
                .orElseThrow(DiscountNotFoundException::new);

        discount.deactivate();

        discounts.save(discount);
    }
}
