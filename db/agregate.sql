create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(name, price)
values ('computer', 10000);

insert into devices(name, price)
values ('phone', 1000);

insert into devices(name, price)
values ('laptop', 5000);

insert into people(name)
values ('Ivan');

insert into people(name)
values ('Petr');

insert into devices_people(device_id, people_id)
values (1, 1);

insert into devices_people(device_id, people_id)
values (2, 1);

insert into devices_people(device_id, people_id)
values (3, 1);

insert into devices_people(device_id, people_id)
values (2, 2);

insert into devices_people(device_id, people_id)
values (3, 2);

--Используя агрегатные функции вывести среднюю цену устройств.
select
avg(price)
from devices
--Используя группировку вывести для каждого человека среднюю цену его устройств.
select
p.name as people_name,
avg(d.price)
from devices_people dp
inner join people p on p.id = dp.people_id
inner join devices d on d.id = dp.device_id
group by p.name
--Дополнить предыдущий  запрос условием, что средняя стоимость устройств должна быть больше 5000.
select
p.name as people_name,
avg(d.price)
from devices_people dp
inner join people p on p.id = dp.people_id
inner join devices d on d.id = dp.device_id
group by p.name
having avg(d.price) > 5000