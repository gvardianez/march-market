create table products (
    id          bigserial primary key,
    title       varchar(255) ,
    price       numeric(8, 2)
);

insert into products (title, price) values
('Bread', 32),
('Milk', 120),
('Butter', 320),
('Cheese', 500);

create table users (
    id          bigserial primary key,
    username        varchar(255) not null,
    password    varchar(80) not null
);

insert into users (username, password) values
('Vasya', '$2a$12$gonsnNDHrKkyzipjr4OEEOvZomer3j3Zql1caWEUeiz9W8nbl18C.'),
('Petya', '$2a$12$2zdwN551vhlOBgOPmChvQOlsEYtmkqexZqB0..3VN.lKnQEmLYeZG');

create table roles (
    id         bigserial primary key,
    name       varchar(50) not null
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

create table authorities (
    id         bigserial primary key,
    name       varchar(50) not null
);

insert into authorities (name)
values ('CAN_READ'),
       ('CAN_WRITE');

create table orders (
    id          bigserial primary key,
    user_id     bigint not null references users (id),
    price       numeric(8, 2)
);

create table order_items (
    id          bigserial primary key,
    order_id    bigint  references orders (id),
    product_id  bigint not null references products (id),
    quantity    int,
    unit_price  numeric(8, 2),
    total_price numeric(8, 2)
);

CREATE TABLE users_roles (
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);

CREATE TABLE roles_authorities (
    role_id bigint not null references roles (id),
    authority_id bigint not null references authorities (id),
    primary key (role_id,authority_id )
);

insert into roles_authorities (role_id,authority_id)
values (1, 1),
       (2, 1),
       (2, 2);
