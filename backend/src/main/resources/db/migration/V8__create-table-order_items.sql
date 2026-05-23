CREATE TABLE order_items (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    order_id UUID NOT NULL,
    game_id UUID NOT NULL,
    price_at_purchase DECIMAL(10,2) NOT NULL CHECK (price_at_purchase >= 0),

    UNIQUE (order_id, game_id),

    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (game_id) REFERENCES games(id)
);
