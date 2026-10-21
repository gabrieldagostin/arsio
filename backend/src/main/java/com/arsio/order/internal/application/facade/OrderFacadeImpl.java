package com.arsio.order.internal.application.facade;

import com.arsio.order.api.dto.GameSalesView;
import com.arsio.order.api.facade.OrderFacade;
import com.arsio.order.internal.application.usecase.GetGameSalesUseCase;
import com.arsio.shared.pagination.Pagination;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderFacadeImpl implements OrderFacade {

    private final GetGameSalesUseCase getGameSalesUseCase;

    public OrderFacadeImpl(GetGameSalesUseCase getGameSalesUseCase) {
        this.getGameSalesUseCase = getGameSalesUseCase;
    }

    @Override
    public List<GameSalesView> getGameSales(Pagination pagination) {
        return getGameSalesUseCase.execute(pagination);
    }
}
