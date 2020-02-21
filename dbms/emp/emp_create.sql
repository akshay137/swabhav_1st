use swabhav;

drop table if exists EMP;

create table EMP (
	EMPNO int not null,
    ENAME varchar(10),
    JOB varchar(9),
    MGR int,
    HIREDATE date,
    SAL decimal,
    COMM decimal,
    DEPTNO int not null,
    constraint EMP_FOREIGN_KEY foreign key (DEPTNO) references DEPT(DEPTNO),
    constraint EMP_PRIMARY_KEY primary key (EMPNO)
);

alter table EMP
	add constraint EMP_SELF_KEY foreign key EMP(MGR) references EMP(EMPNO);
