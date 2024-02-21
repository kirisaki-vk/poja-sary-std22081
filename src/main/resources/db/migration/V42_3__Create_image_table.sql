create table if not exists image_process (
    id varchar primary key ,
    original varchar,
    edited varchar not null ,
    operation varchar not null ,
    time varchar default current_timestamp
);
