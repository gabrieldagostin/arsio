-- =========================
-- INDEXES
-- =========================
CREATE INDEX idx_games_title_trgm ON games USING gin (title gin_trgm_ops);
CREATE INDEX idx_orders_user ON orders(user_id);
CREATE INDEX idx_order_items_order ON order_items(order_id);
CREATE INDEX idx_reviews_game ON reviews(game_id);
CREATE INDEX idx_games_developer ON games(developer_id);
CREATE INDEX idx_game_media_game ON game_media(game_id);
CREATE INDEX idx_user_library_user ON user_library(user_id);
CREATE INDEX idx_wishlist_user ON wishlist(user_id);
CREATE INDEX idx_order_items_game ON order_items(game_id);
CREATE INDEX idx_discounts_game ON discounts(game_id);
CREATE INDEX idx_reviews_user ON reviews(user_id);
CREATE INDEX idx_friends_user_id ON friends(user_id);
CREATE INDEX idx_friends_friend_id ON friends(friend_id);
CREATE INDEX idx_messages_conversation_time ON messages (user_min, user_max, created_at);
