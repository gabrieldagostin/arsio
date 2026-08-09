DROP TABLE game_requirements;

CREATE TABLE game_requirements (

    id UUID NOT NULL,

    game_id UUID NOT NULL,

    category VARCHAR(20) NOT NULL,

    operating_system_id UUID,

    processor_id UUID,

    gpu_id UUID,

    ram INTEGER,

    storage INTEGER,

    storage_unit VARCHAR(50),
    
    CONSTRAINT pk_game_requirements
        PRIMARY KEY (id),

    CONSTRAINT ck_game_requirements_category
        CHECK (
            category IN (
                    'RECOMMENDED',
                    'MINIMUM'
                )
            ),

    CONSTRAINT ck_game_requirements_ram_positive
        CHECK (ram > 0),

    CONSTRAINT ck_game_requirements_storage_positive
        CHECK (storage > 0),

    CONSTRAINT fk_requirements_game_id_games
        FOREIGN KEY (game_id)
            REFERENCES games(id),

    CONSTRAINT fk_game_requirements_operating_system_id_operating_system
        FOREIGN KEY (operating_system_id)
            REFERENCES operating_system(id),

    CONSTRAINT fk_game_requirements_processor_id_processors
        FOREIGN KEY (processor_id)
            REFERENCES processors(id),

    CONSTRAINT fk_game_requirements_gpu_id_gpus
        FOREIGN KEY (gpu_id)
            REFERENCES gpus(id)
);