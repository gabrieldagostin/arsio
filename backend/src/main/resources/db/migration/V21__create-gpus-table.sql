CREATE TABLE gpus (

    id UUID NOT NULL,

    manufacturer VARCHAR(50) NOT NULL,

    model VARCHAR(150) NOT NULL,
    
    CONSTRAINT pk_gpus
        PRIMARY KEY (id),

    CONSTRAINT uk_gpus_manufacturer_model
        UNIQUE (manufacturer, model),

    CONSTRAINT ck_gpus_manufacturer_not_blank
        CHECK (char_length(trim(manufacturer)) > 0),
        
    CONSTRAINT ck_gpus_model_not_blank
        CHECK (char_length(trim(model)) > 0)
);