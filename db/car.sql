create table car_bodies(
	id serial primary key,
	name varchar(255)
);

create table car_engines(
	id serial primary key,
	name varchar(255)
);

create table car_transmissions(
	id serial primary key,
	name varchar(255)
);

create table cars(
	id serial primary key,
	name varchar(255),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name)
values ('седан');
insert into car_bodies(name)
values ('хэтчбек');
insert into car_bodies(name)
values ('пикап');

insert into car_engines(name)
values ('sport');
insert into car_engines(name)
values ('common');
insert into car_engines(name)
values ('hard');

insert into car_transmissions(name)
values ('auto');
insert into car_transmissions(name)
values ('manual');
insert into car_transmissions(name)
values ('universal');

insert into cars(name)
values ('lada');
insert into cars(name, body_id, engine_id, transmission_id)
values ('audi', 1, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id)
values ('bmw', 2, 2, 2);
insert into cars(name, body_id, engine_id, transmission_id)
values ('mercedes', 2, 1, 2);

--Вывести список всех машин и все привязанные к ним детали. Нужно учесть, что каких-то деталей машина может и не содержать. В таком случае значение может быть null при выводе (например, название двигателя null);
select
c.id,
c.name as car_name,
b.name as body_name,
e.name as engine_name,
t.name as transmission_name
from cars c
left join car_bodies b on b.id = c.body_id
left join car_engines e on e.id = c.engine_id
left join car_transmissions t on t.id = c.transmission_id
--Вывести кузова, которые не используются НИ в одной машине. "Не используются" значит, что среди записей таблицы cars отсутствует внешние ключи, ссылающие на таблицу car_bodies. Например, Вы добавили в car_bodies "седан", "хэтчбек" и "пикап", а при добавлении в таблицу cars указали только внешние ключи на записи "седан" и "хэтчбек". Запрос, касающийся этого пункта, должен вывести "пикап", т.к. среди машин нет тех, что обладают таким кузовом;
select
*
from car_bodies b
left join cars c on b.id = c.body_id
where c.id is null
--Вывести двигатели, которые не используются НИ в одной машине, аналогично п.2;
select
*
from car_engines e
left join cars c on e.id = c.engine_id
where c.id is null
--Вывести коробки передач, которые не используются НИ в одной машине, аналогично п.2.
select
*
from car_transmissions t
left join cars c on t.id = c.transmission_id
where c.id is null