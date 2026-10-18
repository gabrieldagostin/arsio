package com.arsio.order.internal.domain.model;

import com.arsio.order.internal.domain.valueobject.GatewayTransactionId;
import com.arsio.order.internal.domain.valueobject.OrderId;
import com.arsio.order.internal.domain.valueobject.OrderStatus;
import com.arsio.shared.money.Money;

import java.util.UUID;

public class Order {

    private final OrderId id;
    private final UUID userId;
    private GatewayTransactionId gatewayTransactionId;
    private Money totalPrice;
    private OrderStatus status;

    public Order(OrderId id, UUID userId, GatewayTransactionId gatewayTransactionId, Money totalPrice, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.gatewayTransactionId = gatewayTransactionId;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public OrderId getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public GatewayTransactionId getGatewayTransactionId() {
        return gatewayTransactionId;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
