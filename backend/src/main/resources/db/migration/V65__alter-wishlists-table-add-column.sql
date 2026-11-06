ALTER TABLE wishlist
    RENAME TO wishlists;

ALTER TABLE wishlists
    ADD COLUMN created_at TIMESTAMPTZ NOT NULL DEFAULT NOW();