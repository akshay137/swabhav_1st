use swabhav;

create table REGIONS (
	REGION_ID int not null,
    REGION_NAME varchar(25)
);

alter table regions
	add constraint reg_id_pk primary key
    (region_id);

create table COUNTRIES (
	COUNTRY_ID char(2) not null,
    COUNTRY_NAME varchar(40),
    REGION_ID int,
    constraint COUNTRY_C_ID_PK primary key
     (COUNTRY_ID)
);

alter table COUNTRIES
	add constraint COUNTER_REG_FK
		foreign key (REGION_ID)
        references REGIONS(REGION_ID)