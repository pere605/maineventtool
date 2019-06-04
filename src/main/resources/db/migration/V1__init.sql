CREATE TABLE seasonal_events
(
    id uuid default uuid_generate_v4() not null constraint seasonal_events_pkey primary key,
    event_id integer not null constraint seasonal_events_event_id_key unique,
    name varchar not null,
    type varchar not null,
    sub_type varchar not null,
    starting_time timestamp not null,
    ending_time timestamp not null
);

CREATE TABLE episodes
(
    number integer not null,
    seasonal_event_id uuid not null,
    reward_type varchar not null,
    reward_sub_type varchar not null,
    reward_amount integer not null,
    chest_id varchar,
    id uuid not null constraint episodes_pk primary key,
    constraint number_seasonal_event_id_chest_id unique (number, seasonal_event_id, chest_id)
);

CREATE UNIQUE index episodes_id_uindex ON episodes (id);
