CREATE TABLE processors (
    id UUID NOT NULL,

    manufacturer VARCHAR(50) NOT NULL,

    model VARCHAR(150) NOT NULL,
    
    CONSTRAINT pk_processors
        PRIMARY KEY (id),

    CONSTRAINT uk_processors_manufacturer_model
        UNIQUE (manufacturer, model),
        
    CONSTRAINT ck_processors_manufacturer_not_blank
        CHECK (char_length(trim(manufacturer)) > 0),
        
    CONSTRAINT ck_processors_model_not_blank
        CHECK (char_length(trim(model)) > 0)
);