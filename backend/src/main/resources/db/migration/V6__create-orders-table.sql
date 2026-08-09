CREATE TABLE orders (

    id UUID NOT NULL,

    user_id UUID NOT NULL,

    gateway_transaction_id VARCHAR(100),

    total_price DECIMAL(10,2) NOT NULL ,

    status VARCHAR(30) NOT NULL DEFAULT 'PENDING',

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    
    CONSTRAINT pk_orders
        PRIMARY KEY (id),

    CONSTRAINT ck_orders_gateway_transaction_id_not_blank
        CHECK (gateway_transaction_id IS NULL
            OR char_length(trim(gateway_transaction_id)) > 0),

    CONSTRAINT ck_orders_total_price_positive
        CHECK (total_price >= 0),

    CONSTRAINT ck_orders_status
        CHECK (
            status IN (
                'PENDING', 
                'COMPLETED', 
                'CANCELLED', 
                'FAILED'
            )
        ),

    CONSTRAINT fk_orders_user_id_users
        FOREIGN KEY (user_id)
            REFERENCES users(id)
);