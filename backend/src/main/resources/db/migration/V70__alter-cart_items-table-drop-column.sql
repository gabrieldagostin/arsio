ALTER TABLE cart_items
DROP COLUMN id;

ALTER TABLE cart_items
RENAME COLUMN created_at TO added_at;

ALTER TABLE cart_items
ADD CONSTRAINT pk_cart_items
PRIMARY KEY (cart_id, game_id);