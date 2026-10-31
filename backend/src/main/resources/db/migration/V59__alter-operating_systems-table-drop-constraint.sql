ALTER TABLE operating_system
    RENAME TO operating_systems;

ALTER TABLE operating_systems
    DROP CONSTRAINT uk_operating_systems_name;