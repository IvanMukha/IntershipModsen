create table person
(
    id            integer generated by default as identity
        primary key,
    full_name     varchar(100) not null
        unique,
    year_of_birth integer      not null
);

alter table person
    owner to postgres;
