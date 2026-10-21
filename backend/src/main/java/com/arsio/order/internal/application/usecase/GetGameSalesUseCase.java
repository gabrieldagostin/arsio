package com.arsio.order.internal.application.usecase;

import com.arsio.order.api.dto.GameSalesView;
import com.arsio.order.internal.domain.repository.OrderRepository;
import com.arsio.shared.pagination.Pagination;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GetGameSalesUseCase {

    private final OrderRepository orders;

    public GetGameSalesUseCase(OrderRepository orders) {
        this.orders = orders;
    }

    public List<GameSalesView> execute(Pagination pagination) {
        return orders.findBestSellingGames(pagination);
    }
}
