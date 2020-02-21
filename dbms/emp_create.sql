use swabhav;
create table departments (
	deptId int primary key auto_increment not null,
    deptName varchar(30),
    deptLocation varchar(50)
);
create table employees (
	empId int primary key auto_increment not null,
    empName varchar(100),
    empSalary int,
    deptId int not null,
    foreign key (deptId) references departments(deptId)
);