create table if not exists person
(
    id serial primary key,
    user_name varchar(255) not null,
    password varchar(255) not null
);