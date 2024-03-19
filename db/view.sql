create table owner_car(
	id serial primary key,
	name varchar(255),
	car_id int references cars(id)
);

insert into owner_car(name, car_id)
values ('Ivan', 1);
insert into owner_car(name, car_id)
values ('Ivan', 2);
insert into owner_car(name, car_id)
values ('Boris', 3);
insert into owner_car(name, car_id)
values ('Nikita', 4);

create view view_car_owner
as
select
m.name,
c.name as car_name,
b.name as body_name,
e.name as engine_name,
t.name as transmission_name
from (	select
		name,
		count(name) as count_car
		from owner_car oc
		group by name
		having count(name) > 1) m
left join owner_car oc on oc.name = m.name
left join cars c on oc.car_id = c.id
left join car_bodies b on b.id = c.body_id
left join car_engines e on e.id = c.engine_id
left join car_transmissions t on t.id = c.transmission_id
where b.name  is null and e.name is null and t.name is null;

select
*
from view_car_owner