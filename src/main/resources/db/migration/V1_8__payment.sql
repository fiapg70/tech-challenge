CREATE TABLE IF NOT EXISTS tb_payment (
    id bigserial PRIMARY KEY,
    client_id bigint not null,
    order_id bigint not null,
    status bigint not null
);