use swabhav;

select * from EMP;
-- all employees in ascending order of name
select * from EMP order by ENAME;

-- all employees in dept 10
select * from EMP where DEPTNO=10;

-- all employees in dept 10, 20
select * from EMP where DEPTNO in (10, 20);

-- all clerks
select * from EMP where JOB="clerk";

-- employees with null commision
select * from EMP where COMM is null;

-- employees with salary in 2000 to 5000
select * from EMP where SAL between 2000 and 5000 order by SAL asc;

-- annula salary
select ENAME, SAL * 12 as ANNUAL_SALARY from EMP;

-- top 3 earning employees
select * from EMP order by SAL desc limit 3;

-- joining date and tenure
select ENAME,
	date_format(HIREDATE, '%d-%b-%y')
    as JOINING_DATE,
    datediff(now(), HIREDATE) as TENURE_DAYS
    from EMP;

-- all departments from EMP
select distinct DEPTNO from EMP;

-- deptno of scott
select ENAME, DEPTNO
	from EMP where ENAME='scott';

-- all employess in scott's dept
select ENAME, DEPTNO from EMP
	where DEPTNO=(select DEPTNO from EMP
		where ENAME='scott');

-- all employees with same designation as smith
select ENAME, JOB from EMP
	where JOB=(select JOB from EMP
		where ENAME="smith");

-- employee name with department name
select EMP.ENAME, DEPT.DNAME from EMP inner join DEPT on EMP.DEPTNO=DEPT.DEPTNO;

-- employee name with manager name
select x.ENAME as ENAME, y.ENAME as BOSSNAME from EMP as x inner join EMP as y on x.MGR=y.EMPNO order by y.ENAME;

-- employee name with manager name including employees with no manager
select x.ENAME as ENAM, y.ENAME as BOSSNAME from EMP as x left join EMP as y on x.MGR=y.EMPNO;