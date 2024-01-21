create table tb_order (
    id bigserial not null,
    code varchar(255) not null,
    client_id bigint not null,
    status varchar(255) not null,
    primary key (id)
);