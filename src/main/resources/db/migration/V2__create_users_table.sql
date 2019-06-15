CREATE TABLE users
(
    id uuid default uuid_generate_v4() not null constraint users_pkey primary key,
    email varchar not null,
    password varchar not null,
    first_name varchar,
    last_name varchar,
    created_at timestamp not null default current_timestamp
);

create unique index users_email_uindex on users (email);
