create table event_publication (

    id uuid not null,

    completion_attempts integer not null,

    completion_date timestamp(6) with time zone,

    event_type varchar(255) not null,

    last_resubmission_date timestamp(6) with time zone,

    listener_id varchar(255) not null,

    publication_date timestamp(6) with time zone not null,

    serialized_event varchar(255) not null,

    status varchar(255) check ((status in ('PUBLISHED','PROCESSING','COMPLETED','FAILED','RESUBMITTED'))),

    primary key (id)
)