create table operation
(
    id       bigserial primary key,
    currency varchar(5),
    amount   bigint,
    type     varchar(255),
    created_at timestamp
);
