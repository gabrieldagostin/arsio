CREATE TABLE order_items (

    id UUID NOT NULL,

    order_id UUID NOT NULL,

    game_id UUID NOT NULL,

    price_at_purchase DECIMAL(10,2) NOT NULL,
    
    CONSTRAINT pk_order_itens
        PRIMARY KEY (id),

    CONSTRAINT uk_order_items_order_id_game_id
        UNIQUE (order_id, game_id),

    CONSTRAINT ck_order_items_price_at_purchase_positive
        CHECK (price_at_purchase >= 0),

    CONSTRAINT fk_order_items_order_id_orders
        FOREIGN KEY (order_id) 
            REFERENCES orders(id),

    CONSTRAINT fk_order_items_game_id_games
        FOREIGN KEY (game_id) 
            REFERENCES games(id)
);