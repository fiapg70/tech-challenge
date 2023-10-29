create table tb_product (
    id bigserial not null,
    name varchar(255) not null,
    description varchar(255) not null,
    pic varchar(255) not null,
    price numeric(19,2) not null,
    product_category_id bigint not null,
    restaurant_id bigint not null,
    create_by varchar(255) not null,
    created_date timestamp(6) not null,
    last_modified_by varchar(255),
    last_modified_date timestamp(6),
    status varchar(255) not null,
    primary key (id)
);