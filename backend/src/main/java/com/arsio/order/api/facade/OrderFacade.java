package com.arsio.order.api.facade;

import com.arsio.order.api.dto.GameSalesView;
import com.arsio.shared.pagination.Pagination;

import java.util.List;

public interface OrderFacade {

    List<GameSalesView> getGameSales(Pagination pagination);
}
