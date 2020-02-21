use swabhav;

drop table foo;

create table foo (id int, datum varchar(20));

insert into foo (id, datum) values
	(1, '1'), (2, '2'), (3, '3'), (4, '4'), (-1, '-1'),
    (10, '10'), (100, '100'), (50, '50'), (75, '75');

select * from foo;

alter table foo add constraint FOO_PK primary key (id);