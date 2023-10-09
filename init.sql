create database wfrpgeneratordb;
alter database wfrpgeneratordb owner to postgres;
\connect wfrpgeneratordb

create table npc (
                     id bigserial not null,
                     age integer not null,
                     agility integer not null,
                     ballistic_skill integer not null,
                     eye_color varchar(255),
                     fellowship integer not null,
                     gender varchar(255),
                     hair_color varchar(255),
                     height integer not null,
                     intelligence integer not null,
                     movement integer not null,
                     name varchar(255),
                     strength integer not null,
                     strength_bonus integer not null,
                     toughness integer not null,
                     toughness_bonus integer not null,
                     weapon_skill integer not null,
                     weight integer not null,
                     will_power integer not null,
                     wounds integer not null,
                     race_id bigint,
                     user_id bigint,
                     primary key (id)
);
create table race (
                      id bigserial not null,
                      base_height integer not null,
                      maximum_age integer not null,
                      maximum_weight integer not null,
                      minimum_age integer not null,
                      minimum_weight integer not null,
                      name varchar(255),
                      stats_id bigint,
                      primary key (id)
);
create table race_eye_colors (
                                 race_id bigint not null,
                                 eye_colors varchar(255)
);

create table race_eye_colors_pl (
                                    race_id bigint not null,
                                    eye_colors_pl varchar(255)
);

create table race_hair_colors (
                                  race_id bigint not null,
                                  hair_colors varchar(255)
);

create table race_hair_colors_pl (
                                     race_id bigint not null,
                                     hair_colors_pl varchar(255)
);
    

create table race_stats (
                            id bigserial not null,
                            basic_agility integer not null,
                            basic_ballistic_skill integer not null,
                            basic_fellowship integer not null,
                            basic_intelligence integer not null,
                            basic_strength integer not null,
                            basic_toughness integer not null,
                            basic_weapon_skill integer not null,
                            basic_will_power integer not null,
                            max_wounds integer not null,
                            movement integer not null,
                            primary key (id)
);
    

create table token (
                       id bigserial not null,
                       email varchar(255),
                       expiration_date timestamp(6),
                       is_used boolean not null,
                       token varchar(255),
                       token_type smallint,
                       primary key (id)
);
    

create table user_table (
                            id bigserial not null,
                            email varchar(255) not null,
                            is_confirmed boolean not null,
                            join_date timestamp(6),
                            last_update timestamp(6),
                            password varchar(255) not null,
                            username varchar(255) not null,
                            primary key (id)
);
    

create table user_table_roles (
                                  user_table_id bigint not null,
                                  roles varchar(255)
);
    

create table user_table_saved_npcs (
                                       user_table_id bigint not null,
                                       saved_npcs_id bigint not null
);
    

alter table user_table
    add constraint UK_eamk4l51hm6yqb8xw37i23kb5 unique (email);
    

alter table user_table
    add constraint UK_en3wad7p8qfu8pcmh62gvef6v unique (username);
    

alter table user_table_saved_npcs
    add constraint UK_namaxf5uwygqyekse3fluw0lk unique (saved_npcs_id);
    

alter table npc
    add constraint FK6112nvfo0out6gngcdgv2mlcl
        foreign key (race_id)
            references race
    ;

alter table npc
    add constraint FK12e9ays0yif9vjyybh03aijrd
        foreign key (user_id)
            references user_table
    ;

alter table race
    add constraint FKkmdmsymetxl4ny11btf7k52s1
        foreign key (stats_id)
            references race_stats
    ;

alter table race_eye_colors
    add constraint FKj62dyq41d1mvhw9alov2l7rdg
        foreign key (race_id)
            references race
    ;

alter table race_eye_colors_pl
    add constraint FKlmg2ckhlmtqspvw02y0tq34tf
        foreign key (race_id)
            references race
    ;

alter table race_hair_colors
    add constraint FKji5kbmng1icck4jcitvkpx3sg
        foreign key (race_id)
            references race
    ;

alter table race_hair_colors_pl
    add constraint FKl36175tvw1qa73wxf8apmka9j
        foreign key (race_id)
            references race
    ;

alter table user_table_roles
    add constraint FKmg9qxq6e1b1f6ehv848qf7vcd
        foreign key (user_table_id)
            references user_table
    ;

alter table user_table_saved_npcs
    add constraint FKorlqd70cck4qf7svoqfj9wly0
        foreign key (saved_npcs_id)
            references npc
    ;

alter table user_table_saved_npcs
    add constraint FK8vypcf2293rv61xpinnfn1c4m
        foreign key (user_table_id)
            references user_table;