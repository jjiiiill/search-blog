drop table if exists document;
create table document(
    `document_no`         int auto_increment  primary key     not null,
    `document_title`      varchar(100)                        not null,
    `document_content`    varchar(200)                        not null,
    `document_type`       varchar(10)                         not null,
    `register_member_no`  int                                 not null,
    `register_ymdt`       datetime                            not null
);

drop table if exists document_approver;
create table document_approver(
    `document_approver_no`  int auto_increment  primary key     not null,
    `document_no`           int                                 not null,
    `member_no`             int                                 not null,
    `sequence`              int                                 not null,
    `status`                varchar(10)                         default null,
    `opinion`               varchar(100)                        default null,
    `update_ymdt`           datetime                            not null,
    `register_ymdt`         datetime                            not null,
    unique key `unique_approver` (`document_no`, `member_no`)
);

drop table if exists member;
create table member(
    `member_no`         int auto_increment  primary key     not null,
    `member_id`         varchar(20)                         not null,
    `member_password`   varchar(200)                        not null,
    `member_name`       varchar(50)                         not null,
    `register_ymdt`     datetime                            not null,
    unique key `unique_member_id` (`member_id`)
);