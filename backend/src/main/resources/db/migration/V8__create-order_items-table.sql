CREATE TABLE order_items (

    id UUID PRIMARY KEY,

    order_id UUID NOT NULL,

    game_id UUID NOT NULL,

    price_at_purchase DECIMAL(10,2) NOT NULL,

    CONSTRAINT order_items_order_id_game_id_unique
        UNIQUE (order_id, game_id),

    CONSTRAINT order_items_price_at_purchase_not_negative_check
        CHECK (price_at_purchase >= 0),

    CONSTRAINT order_items_order_id_fk
        FOREIGN KEY (order_id) REFERENCES orders(id),

    CONSTRAINT order_items_game_id_fk
        FOREIGN KEY (game_id) REFERENCES games(id)
);
