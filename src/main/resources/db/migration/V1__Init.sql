create table if not exists ${flyway:defaultSchema}.region
(
    id          varchar(255) not null
                    constraint region_pkey
                        primary key,
    name        varchar(255) not null
);

create table if not exists ${flyway:defaultSchema}.award
(
    id          varchar(255) not null
                    constraint award_pkey
                        primary key,
    name        varchar(255) not null,
    importance  varchar(10)  not null
);

create table if not exists ${flyway:defaultSchema}.comment
(
    id                 varchar(255)  not null
                           constraint comment_pkey
                               primary key,
    text               varchar(255)  not null,
    hidden             boolean       default false,
    creation_time      timestamp     not null,
    last_modified_time timestamp     not null
);

create table if not exists ${flyway:defaultSchema}.club
(
    id         varchar(255) not null
                           constraint club_pkey
                               primary key,
    name               varchar(255) not null,
    creation_time      timestamp     not null,
    last_modified_time timestamp     not null
);

create table if not exists ${flyway:defaultSchema}.file_meta
(
    id                 varchar(255) not null
                           constraint file_meta_pkey
                               primary key,
    name               varchar(255) not null,
    type               varchar(255) not null,
    external_id        varchar(255),
    deleted            boolean default false,
    creation_time      timestamp     not null,
    last_modified_time timestamp     not null
);

create table if not exists ${flyway:defaultSchema}.pet_snail
(
    id                  varchar(255) not null
                            constraint pet_snail_pkey
                                primary key,
    mucus_level        varchar(10),
    age                integer,
    first_name         varchar(255),
    last_name          varchar(255),
    creation_time      timestamp     not null,
    last_modified_time timestamp     not null
);

create table if not exists ${flyway:defaultSchema}.resident
(
    id                 varchar(255) not null
                            constraint resident_pkey
                                primary key,
    first_name         varchar(255),
    last_name          varchar(255),
    comment_id         varchar(255) references comment (id),
    region_id          varchar(255) references region (id),
    creation_time      timestamp     not null,
    last_modified_time timestamp     not null
);

create table if not exists ${flyway:defaultSchema}.resident_local
(
    id          varchar(255) not null
                    constraint resident_local_pkey
                        primary key,
    club_id     varchar(255) references club (id)
);

create table if not exists ${flyway:defaultSchema}.resident_mystic
(
    id                      varchar(255) not null
                                constraint resident_mystic_pkey
                                    primary key,
    appearance_per_year     integer
);

create table if not exists ${flyway:defaultSchema}.resident_file_meta
(
    resident_id   varchar(255)
                        constraint fk__resident_file_meta
                            references resident (id),
    file_id        varchar(255)
                        constraint fk__file_meta_resident
                            references file_meta (id)
);

create table if not exists ${flyway:defaultSchema}.resident_local_award
(
    resident_local_id   varchar(255)
                            constraint fk__resident_local_award
                                references resident_local (id),
    award_id            varchar(255)
                            constraint fk__award_resident_local
                                references award (id)
);

