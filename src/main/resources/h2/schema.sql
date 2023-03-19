drop table if exists test;
create table test(
    `test1`       int auto_increment  primary key     not null,
    `test2`       varchar(100)                        not null,
    `test3`       varchar(200)                        not null,
    `test4`       datetime                            not null
);
