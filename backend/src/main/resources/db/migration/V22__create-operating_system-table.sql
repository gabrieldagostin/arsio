CREATE TABLE operating_system (

    id UUID NOT NULL,

    name VARCHAR(50) NOT NULL,
    
    CONSTRAINT pk_operating_system
        PRIMARY KEY (id),

    CONSTRAINT uk_operating_system_name
        UNIQUE (name),

    CONSTRAINT ck_operating_system_name_not_blank
        CHECK (char_length(trim(name)) > 0)
);