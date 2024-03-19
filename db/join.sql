create table departments(
	name varchar(255),
	employee varchar(255) unique,
	primary key (name, employee)
);

create table employees(
	name varchar(255) references departments(employee)
);

insert into departments(name, employee)
values ('Administrative department', 'Ivanov');
insert into departments(name, employee)
values ('Administrative department', 'Petrov');
insert into departments(name, employee)
values ('Administrative department', 'Sidorov');
insert into departments(name, employee)
values ('Legal Department', 'Kozlov');
insert into departments(name, employee)
values ('Legal Department', 'Popov');
insert into departments(name, employee)
values ('Empty Department', '');

insert into employees(name)
values ('Ivanov');
insert into employees(name)
values ('Petrov');
insert into employees(name)
values ('Sidorov');
insert into employees(name)
values ('Kozlov');
insert into employees(name)
values ('Popov');
--Выполнить запросы с left, right, full, cross соединениями
select
*
from employees e
left join departments d on e.name = d.employee;
select
*
from employees e
right join departments d on e.name = d.employee;
select
*
from employees e
full join departments d on e.name = d.employee;
--Используя left join найти департаменты, у которых нет работников
select
*
from departments d
left join employees e on e.name = d.employee
where e.name is null;
--Используя left и right join написать запросы, которые давали бы одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный).
select
e.name,
d.name,
d.employee
from employees e
left join departments d on e.name = d.employee;
select
e.name,
d.name,
d.employee
from departments d
right join employees e on e.name = d.employee;

create table teens(
	name varchar(255),
	gender varchar(1)
);

insert into teens(name, gender)
values ('Вася', 'М');
insert into teens(name, gender)
values ('Миша', 'М');
insert into teens(name, gender)
values ('Ваня', 'М');
insert into teens(name, gender)
values ('Маша', 'Ж');
insert into teens(name, gender)
values ('Света', 'Ж');
insert into teens(name, gender)
values ('Катя', 'Ж');

--Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары. Исключите дублирование пар вида Вася-Маша и Маша-Вася.
select
*
from (
	select
	t1.name as M_name,
	t1.gender as M,
	t2.name as F_name,
	t2.gender as F
	from teens t1
	cross join teens t2 ) t
where t.M = 'М' AND t.F = 'Ж'