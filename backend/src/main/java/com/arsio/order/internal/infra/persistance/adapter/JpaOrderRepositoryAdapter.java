package com.arsio.order.internal.infra.persistance.adapter;

import com.arsio.order.api.dto.GameSalesView;
import com.arsio.order.internal.domain.repository.OrderRepository;
import com.arsio.order.internal.domain.valueobject.OrderStatus;
import com.arsio.order.internal.infra.persistance.mapper.OrderEntityMapper;
import com.arsio.order.internal.infra.persistance.repository.SpringDataOrderRepository;
import com.arsio.shared.pagination.Pagination;
import com.arsio.shared.pagination.PaginationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaOrderRepositoryAdapter implements OrderRepository {

    private final OrderEntityMapper mapper;
    private final PaginationMapper paginationMapper;
    private final SpringDataOrderRepository orders;

    @Override
    public List<GameSalesView> findBestSellingGames(Pagination pagination) {

        Pageable pageable = paginationMapper.toPageable(pagination);

        return orders.findBestSellingGames(OrderStatus.COMPLETED, pageable);
    }
}
