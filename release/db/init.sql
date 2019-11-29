create table address
(
    id       varchar(255) not null
        primary key,
    city     varchar(255) null,
    detail   varchar(255) null,
    province varchar(255) null
);

create table employee
(
    id             varchar(255) not null
        primary key,
    create_time    datetime     null,
    is_delete      int          not null,
    update_time    datetime     null,
    account_number varchar(255) null,
    employee_name  varchar(255) null,
    merchant_id    bigint       null,
    note           varchar(255) null,
    password       varchar(255) null,
    phone_number   varchar(255) null,
    role_id        varchar(255) null,
    sex            int          null,
    shop_id        bigint       null,
    work_number    varchar(255) null
);

create table orders
(
    id          varchar(255)   not null
        primary key,
    created_at  datetime       null,
    status      int            null,
    total_price decimal(19, 2) null
);

create table orders_item
(
    id         varchar(255)   not null
        primary key,
    count      int            not null,
    item_price decimal(19, 2) null,
    product_id varchar(255)   null,
    order_id   varchar(255)   null,
    constraint FKqa7i0ev3xqm2d6t93n9blxef1
        foreign key (order_id) references orders (id)
);

create table sys_permission
(
    id              varchar(255) not null
        primary key,
    create_time     datetime     null,
    is_delete       int          not null,
    update_time     datetime     null,
    menu_code       varchar(255) null,
    menu_name       varchar(255) null,
    permission_code varchar(255) null,
    permission_name varchar(255) null,
    required        int          null,
    permission_type varchar(255) null
);

create table sys_role
(
    id          varchar(255) not null
        primary key,
    create_time datetime     null,
    is_delete   int          not null,
    update_time datetime     null,
    role_name   varchar(255) null
);

create table sys_role_permission
(
    role_id       varchar(255) not null,
    permission_id varchar(255) not null,
    constraint FK9q28ewrhntqeipl1t04kh1be7
        foreign key (role_id) references sys_role (id),
    constraint FKomxrs8a388bknvhjokh440waq
        foreign key (permission_id) references sys_permission (id)
);

