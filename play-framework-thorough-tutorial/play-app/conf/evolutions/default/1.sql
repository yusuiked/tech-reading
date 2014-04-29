# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table member (
  id                        bigint not null,
  name                      varchar(255),
  mail                      varchar(255),
  tel                       varchar(255),
  constraint pk_member primary key (id))
;

create table message (
  id                        bigint not null,
  name                      varchar(255),
  message                   varchar(255),
  member_id                 bigint,
  postdate                  timestamp not null,
  constraint pk_message primary key (id))
;


create table member_message (
  member_id                      bigint not null,
  message_id                     bigint not null,
  constraint pk_member_message primary key (member_id, message_id))
;
create sequence member_seq;

create sequence message_seq;

alter table message add constraint fk_message_member_1 foreign key (member_id) references member (id) on delete restrict on update restrict;
create index ix_message_member_1 on message (member_id);



alter table member_message add constraint fk_member_message_member_01 foreign key (member_id) references member (id) on delete restrict on update restrict;

alter table member_message add constraint fk_member_message_message_02 foreign key (message_id) references message (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists member;

drop table if exists member_message;

drop table if exists message;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists member_seq;

drop sequence if exists message_seq;

