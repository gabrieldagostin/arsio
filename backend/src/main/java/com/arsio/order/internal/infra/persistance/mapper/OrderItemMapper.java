package com.arsio.order.internal.infra.persistance.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.order.internal.domain.model.OrderItem;
import com.arsio.order.internal.domain.valueobject.OrderId;
import com.arsio.order.internal.infra.persistance.entity.OrderItemEntity;
import com.arsio.shared.money.Money;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface OrderItemMapper {

    @Mapping(target = "id", source = "orderItemEntity.id")
    @Mapping(target = "orderId", source = "orderItemEntity.order.id")
    OrderItem toDomain(OrderItemEntity orderItemEntity);

    @Mapping(target = "id", source = "orderItem.id")
    @Mapping(target = "order", source = "orderItemEntity.order")
    @Mapping(target = "gameId", source = "orderItem.gameId")
    @Mapping(target = "priceAtPurchase", source = "orderItem.priceAtPurchase")
    OrderItemEntity toEntity(OrderItem orderItem, OrderItemEntity orderItemEntity);

    default UUID orderIdToUuid(OrderId orderId) {
        return orderId.value();
    }

    default OrderId uuidToOrderId(UUID value) {
        return new OrderId(value);
    }

    default BigDecimal moneyToBigDecimal(Money money) {
        return money.amount();
    }

    default Money bigDecimalToMoney(BigDecimal value) {
        return new Money(value);
    }
}
