CREATE TABLE orders (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid() ,
    user_id UUID NOT NULL,
    gateway_transaction_id VARCHAR(100) CHECK (gateway_transaction_id IS NULL OR trim(gateway_transaction_id) <> ''),
    total_price DECIMAL(10,2) NOT NULL CHECK (total_price >= 0),
    status order_status NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
