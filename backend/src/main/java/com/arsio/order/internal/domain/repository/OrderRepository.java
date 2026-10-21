package com.arsio.order.internal.domain.repository;

import com.arsio.order.api.dto.GameSalesView;
import com.arsio.shared.pagination.Pagination;

import java.util.List;

public interface OrderRepository {

    List<GameSalesView> findBestSellingGames(Pagination pagination);
}
