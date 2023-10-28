create table tb_restaurant (
    id bigserial not null,
    name varchar(255) not null,
    cnpj varchar(255) not null,
    create_by varchar(255) not null,
    created_date timestamp(6) not null,
    last_modified_by varchar(255),
    last_modified_date timestamp(6),
    status varchar(255) not null,
    primary key (id)
);