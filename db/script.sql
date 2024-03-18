create table machine (
	id serial primary key,
	type text,
	speed int,
	name varchar(255)
);

insert into machine (type, speed, name) values ('ground', 100, 'car');
select * from machine;
update machine set speed = 120;
select * from machine;
delete from machine;
select * from machine;