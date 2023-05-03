create table if not exists ingredients
(
    id serial primary key,
    name varchar(255) not null,
    price decimal not null,
    ingredient_type_id int references ingredients(id),
    burger_id int references burgers(id)
);