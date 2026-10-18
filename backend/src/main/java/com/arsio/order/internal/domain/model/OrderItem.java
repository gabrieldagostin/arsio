package com.arsio.order.internal.domain.model;

import com.arsio.order.internal.domain.valueobject.OrderId;
import com.arsio.shared.money.Money;

import java.util.UUID;

public class OrderItem {

    private final OrderId id;
    private final OrderId orderId;
    private final UUID gameId;
    private final Money priceAtPurchase;

    public OrderItem(OrderId id, OrderId orderId, UUID gameId, Money priceAtPurchase) {
        this.id = id;
        this.orderId = orderId;
        this.gameId = gameId;
        this.priceAtPurchase = priceAtPurchase;
    }

    public OrderId getId() {
        return id;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public UUID getGameId() {
        return gameId;
    }

    public Money getPriceAtPurchase() {
        return priceAtPurchase;
    }
}
