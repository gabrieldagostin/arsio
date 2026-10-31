package com.arsio.order.internal.infra.persistance.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.order.internal.domain.model.Order;
import com.arsio.order.internal.domain.valueobject.GatewayTransactionId;
import com.arsio.order.internal.domain.valueobject.OrderId;
import com.arsio.order.internal.domain.valueobject.OrderStatus;
import com.arsio.order.internal.infra.persistance.entity.OrderEntity;
import com.arsio.shared.money.Money;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface OrderEntityMapper {

    Order toDomain(OrderEntity orderEntity);

    @Mapping(target = "createdAt", ignore = true)
    OrderEntity toEntity(Order order);

    default UUID orderIdToUuid(OrderId orderId) {
        return orderId.value();
    }

    default OrderId uuidToOrderId(UUID value) {
        return new OrderId(value);
    }

    default String gatewayTransactionIdToString(GatewayTransactionId gatewayTransactionId) {
        return gatewayTransactionId.value();
    }

    default GatewayTransactionId stringToGatewayTransactionId(String value) {
        return new GatewayTransactionId(value);
    }

    default BigDecimal moenyToBigDecimal(Money money) {
        return money.amount();
    }

    default Money bigDecimalToMoney(BigDecimal value) {
        return new Money(value);
    }

    default String orderStatusToString(OrderStatus orderStatus) {
        return orderStatus.name();
    }

    default OrderStatus stringToOrderStatus(String value) {
        return OrderStatus.valueOf(value);
    }
}
