create table categories
(
    id         bigserial primary key,
    title      varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into categories (title)
values ('Food'),
       ('Electronic');

create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       numeric(8, 2),
    category_id bigint references categories (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

insert into products (title, price, category_id)
values ('Bread', 32.00, 1),
       ('Milk', 120.00, 1),
       ('Butter', 320.00, 1),
       ('Cheese', 500.00, 1);

create table users
(
    id         bigserial primary key,
    username   varchar(255) not null,
    password   varchar(80)  not null,
    email      varchar(255) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into users (username, password, email)
values ('Vasya', '$2a$12$gonsnNDHrKkyzipjr4OEEOvZomer3j3Zql1caWEUeiz9W8nbl18C.', 'vasya@mail.ru'),
       ('Petya', '$2a$12$2zdwN551vhlOBgOPmChvQOlsEYtmkqexZqB0..3VN.lKnQEmLYeZG', 'petya@mail.ru');

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

create table authorities
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into authorities (name)
values ('CAN_READ'),
       ('CAN_WRITE');

create table orders
(
    id         bigserial primary key,
    user_id    bigint not null references users (id),
    price      numeric(8, 2),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table order_items
(
    id          bigserial primary key,
    order_id    bigint references orders (id),
    product_id  bigint not null references products (id),
    quantity    int,
    unit_price  numeric(8, 2),
    total_price numeric(8, 2),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

CREATE TABLE users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);

CREATE TABLE roles_authorities
(
    role_id      bigint not null references roles (id),
    authority_id bigint not null references authorities (id),
    primary key (role_id, authority_id)
);

insert into roles_authorities (role_id, authority_id)
values (1, 1),
       (2, 1),
       (2, 2);

