create table rentbook
(
    id           integer
        constraint freebook_id_fkey
            references book
            on delete cascade,
    timeofrent   date,
    timeofreturn date
);

alter table rentbook
    owner to postgres;

