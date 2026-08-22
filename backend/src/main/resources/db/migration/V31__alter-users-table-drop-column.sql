ALTER TABLE users
DROP COLUMN active;

ALTER TABLE users
ADD COLUMN status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE';

ALTER TABLE users
ADD CONSTRAINT ck_users_status
CHECK (
    status IN (
        'ACTIVE',
        'INACTIVE',
        'PENDING',
        'SUSPENDED',
        'BANNED'
        )
    );