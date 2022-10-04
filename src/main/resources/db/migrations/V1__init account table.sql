create table operation
(
    id         bigserial primary key,
    currency   varchar(5),
    amount     bigint,
    account_id varchar(10),
    type       varchar(255),
    created_at timestamp
);
