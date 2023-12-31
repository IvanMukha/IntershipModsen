create table book
(
    id          integer generated by default as identity
        primary key,
    isbn        varchar(100) not null,
    title       varchar(100) not null,
    genre       varchar(100) not null,
    description varchar(255) not null,
    author      varchar(100) not null,
    person_id   integer
                             references person
                                 on delete set null
);

alter table book
    owner to postgres;

