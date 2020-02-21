use swabhav;

create table REGIONS (
	REGION_ID int not null,
    REGION_NAME varchar(25)
);

alter table REGIONS
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
        references REGIONS(REGION_ID);

create table LOCATIONS (
	LOCATION_ID int not null,
    STREET_ADDRESS varchar(40),
    POSTAL_CODE varchar(12),
    CITY varchar(30) not null,
    STATE_PROVINENCE varchar(25),
    COUNTRY_ID char(2)
);

alter table LOCATIONS
	add constraint LOC_ID_PK primary key (LOCATION_ID),
	add constraint LOC_C_ID_FK foreign key (COUNTRY_ID) references COUNTRIES(COUNTRY_ID);