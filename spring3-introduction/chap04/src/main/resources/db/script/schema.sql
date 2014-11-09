create table OWNER (
  OWNER_NAME varchar(255) primary key
);

create table PET (
  PET_ID identity,
  PET_NAME varchar(255),
  OWNER_NAME varchar(255),
  PRICE int,
  BIRTH_DATE DATE
);

alter table PET add foreign key(OWNER_NAME) references OWNER(OWNER_NAME);
