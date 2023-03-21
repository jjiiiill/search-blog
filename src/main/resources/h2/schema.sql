drop table if exists search_history;
create table search_history(
    `id`            bigint auto_increment  primary key  not null,
    `keyword`       varchar(100)                        not null,
    `search_ymdt`   datetime                            not null
);
