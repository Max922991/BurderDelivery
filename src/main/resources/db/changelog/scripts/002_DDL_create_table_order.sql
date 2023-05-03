create table if not exists orders
(
    id serial primary key,
    address varchar(255) not null,
    date_time timestamp,
    is_ready boolean
);