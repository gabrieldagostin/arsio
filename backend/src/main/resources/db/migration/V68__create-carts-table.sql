CREATE TABLE carts (

    id UUID NOT NULL,

    user_id UUID NOT NULL,

    status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_cart
        PRIMARY KEY (id),

    CONSTRAINT ck_cart_status
        CHECK (
            type IN (
                     'ABANDONED',
                     'ACTIVE'
                )
            ),

    CONSTRAINT fk_cart_user_id_users
        FOREIGN KEY (user_id)
            REFERENCES users(id)
);