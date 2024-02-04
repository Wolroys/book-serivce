--liquibase formatted sql

--changeset Aleksey:1
create table if not exists resource(
    id bigserial primary key,
    name varchar(255) not null,
    availability boolean default true
);


--changeset Aleksey:2
create table if not exists hotel(
    id bigserial primary key references resource(id) on delete cascade,
    rating int,
    num_rooms int
);

--changeset Aleksey:3
create table if not exists restaurant(
    id bigserial primary key,
    cuisine_type varchar(255),
    seating_capacity int,
    foreign key (id) references resource(id) on delete cascade
);

--changeset Aleksey:4
create table if not exists conference_room(
    id bigserial primary key,
    capacity int,
    projector_available boolean,
    foreign key (id) references resource(id) on delete cascade
);

--changeset Aleksey:5
create table if not exists doctor_office(
    id bigserial primary key,
    medical_specialty varchar(255),
    working_house varchar(255),
    foreign key (id) references resource(id) on delete cascade
);

--changeset Aleksey:6
create table if not exists reservation_period(
    id bigserial primary key,
    user_id bigint,
    resource_id bigint references resource(id),
    start_time varchar(255) not null,
    end_time varchar(255) not null
);
