create table if not exists ingredient_types
(
    id   serial primary key,
    name varchar(255) not null
);