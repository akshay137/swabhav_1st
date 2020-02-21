use swabhav;

-- display no of employees
select count(EMPNO) from EMP;

-- display sum of salaries of employees
select sum(SAL) from EMP;

-- display average salary of employees
select avg(SAL) from EMP;

-- display sum, average, count of employees
select sum(SAL) as TOTAL_SALARY,
	avg(SAL) as AVERAGE_SALARY,
    count(EMPNO) as TOTAL_EMPLOYEES
    from EMP;


-- display department wise headcount
select DEPTNO, count(EMPNO) as HEAD_COUNT from EMP group by DEPTNO;

-- display job wise headcount
select JOB, count(EMPNO) as HEAD_COUNT from EMP group by JOB;

-- disply department and job wise headcount
select DEPTNO, JOB, count(EMPNO) as HEAD_COUNT
	from EMP group by DEPTNO, JOB;

-- deptwise headcount greater than 2 in dept 10, 20, sort in desc by headcount
select DEPTNO, count(EMPNO) as HEAD_COUNT from EMP
	where DEPTNO in (10, 20)
    group by DEPTNO having HEAD_COUNT > 2
    order by HEAD_COUNT desc;

-- display ename and deptname
select EMP.ENAME, DEPT.DNAME from EMP inner join DEPT on EMP.DEPTNO=DEPT.DEPTNO;

-- display department name wise count
select DEPT.DNAME, count(EMP.EMPNO) as HEAD_COUNT
	from EMP inner join DEPT on EMP.DEPTNO=DEPT.DEPTNO
    group by DEPT.DNAME;

-- display department name, jobwise headcount
select DEPT.DNAME, EMP.JOB, count(EMP.EMPNO) as HEAD_COUNT
	from EMP inner join DEPT on EMP.DEPTNO=DEPT.DEPTNO
    group by DEPT.DNAME, EMP.JOB;

-- display all departments with employees, null if no employees
select DEPT.DEPTNO, DEPT.DNAME, EMP.EMPNO, EMP.ENAME
	from DEPT left join EMP
    on DEPT.DEPTNO=EMP.DEPTNO;

-- display department with no employees
select DEPT.DNAME, count(EMP.EMPNO) as HEAD_COUNT
	from DEPT left join EMP on DEPT.DEPTNO=EMP.DEPTNO
    group by DEPT.DNAME having HEAD_COUNT=0;

-- employee name with manager name
select x.ENAME as ENAME, y.ENAME as BOSSNAME
	from EMP as x
    inner join EMP as y
    on x.MGR=y.EMPNO order by y.ENAME;

-- employee name with manager name including employees with no manager
select x.ENAME as ENAME, y.ENAME as BOSSNAME
	from EMP as x
    left join EMP as y on x.MGR=y.EMPNO;

-- display empname, bossname, deptname
select e.ENAME, b.ENAME as BOSS, d.DNAME as DEPARTMENT
from EMP as e
inner join EMP as b on e.MGR=b.EMPNO
inner join DEPT as d on e.DEPTNO=d.DEPTNO;