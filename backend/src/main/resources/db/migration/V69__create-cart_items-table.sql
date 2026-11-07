CREATE TABLE cart_items (

    id UUID NOT NULL,

    cart_id UUID NOT NULL,

    game_id UUID NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_cart_items
        PRIMARY KEY (id),

    CONSTRAINT fk_cart_items_cart_id_cart
        FOREIGN KEY (cart_id)
            REFERENCES cart(id),

    CONSTRAINT fk_cart_items_game_id
        FOREIGN KEY (game_id)
            REFERENCES games(id)
);