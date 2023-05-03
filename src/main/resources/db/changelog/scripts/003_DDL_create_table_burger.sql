create table if not exists burgers
(
    id serial primary key,
    name varchar(255) not null,
    description varchar(255) not null,
    price decimal not null,
    is_spicy boolean not null,
    order_id int references orders(id),
    menu_id int references orders(id)
);