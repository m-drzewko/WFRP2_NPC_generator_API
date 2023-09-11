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
create table race_hair_colors (
                                  race_id bigint not null,
                                  hair_colors varchar(255)
);