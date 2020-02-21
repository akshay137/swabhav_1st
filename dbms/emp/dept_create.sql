use swabhav;

create table DEPT (
	DEPTNO int not null,
    DNAME varchar(14),
    LOC varchar(13),
    constraint DEPT_PRIMARY_KEY primary key (DEPTNO)
);

insert into DEPT values
	(10, 'ACCOUNTING', 'NEW YORK');
insert into DEPT values
	(20, 'RESEARCH', 'DALLAS');
insert into DEPT values
	(30, 'SALES', 'CHICAGO');
insert into DEPT values
	(40, 'OPERATIONS', 'BOSTON');