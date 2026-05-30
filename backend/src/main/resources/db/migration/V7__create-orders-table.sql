CREATE TABLE orders (

    id UUID PRIMARY KEY,

    user_id UUID NOT NULL,

    gateway_transaction_id VARCHAR(100),

    total_price DECIMAL(10,2) NOT NULL ,

    status order_status NOT NULL DEFAULT 'PENDING',

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT orders_gateway_transaction_id_not_blank_check
        CHECK (gateway_transaction_id IS NULL
            OR char_length(trim(gateway_transaction_id)) > 0),

    CONSTRAINT orders_total_price_not_negative_check
        CHECK (total_price >= 0),

    CONSTRAINT orders_user_id_fk
        FOREIGN KEY (user_id)
            REFERENCES users(id)
);
