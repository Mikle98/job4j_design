create table type(
	id serial primary key,
	name varchar(255)
);

create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date timestamp,
	price float
);

insert into type(name)
values ('молоко');

insert into type(name)
values ('сыр');

insert into type(name)
values ('неизвестно');

insert into product(name,type_id, expired_date, price)
values ('Молоко 1.5', 1, '2010-01-01', 200);

insert into product(name,type_id, expired_date, price)
values ('Молоко 2.5', 1, '2024-06-01', 250);

insert into product(name,type_id, expired_date, price)
values ('Молоко 3.5', 1, '1000-01-01', 350);

insert into product(name,type_id, price)
values ('Сыр плавленный', 2, 300);

insert into product(name,type_id, expired_date, price)
values ('Сыр плавленный', 2, '2000-01-01', 50);

insert into product(name,type_id, expired_date, price)
values ('Сыр моцарелла', 2, '2025-01-01', 150);

insert into product(name,type_id, price)
values ('Сыр плавленный', 2, 300);

insert into product(name,type_id, expired_date, price)
values ('Сыр плавленный', 2, '2000-01-01', 50);

insert into product(name,type_id, expired_date, price)
values ('Сыр моцарелла', 2, '2025-01-01', 150);

insert into product(name,type_id, price)
values ('Сыр плавленный', 2, 300);

insert into product(name,type_id, expired_date, price)
values ('Сыр плавленный', 2, '2000-01-01', 50);

insert into product(name,type_id, expired_date, price)
values ('Сыр моцарелла', 2, '2025-01-01', 150);

insert into product(name,type_id, price)
values ('Сыр плавленный', 2, 300);

insert into product(name,type_id, expired_date, price)
values ('Сыр плавленный', 2, '2000-01-01', 50);

insert into product(name,type_id, expired_date, price)
values ('Сыр моцарелла', 2, '2025-01-01', 150);

--Написать запрос получение всех продуктов с типом "СЫР"
select
p.*
from product p
inner join type t on t.id = p.type_id and t.name like 'сыр'
--Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select
*
from product
where name like '%мороженое%'
--Написать запрос, который выводит все продукты, срок годности которых уже истек
select
*
from product
where expired_date < current_date
--Написать запрос, который выводит самый дорогой продукт. Запрос должен быть универсальный и находить все продукты с максимальной ценой.
select
name,
max(price)
from product
group by name
--Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
select
t.name,
count(p.*)
from product p
inner join type t on t.id = p.type_id
group by t.name
--Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select
p.*
from product p
inner join type t on t.id = p.type_id
				and (t.name like 'сыр' or t.name like 'молоко')
-- Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук. Под количеством подразумевается количество продуктов определенного типа. Например, если есть тип "СЫР" и продукты "Сыр плавленный" и "Сыр моцарелла", которые ему принадлежат, то количество продуктов типа "СЫР" будет 2.
select
t.name,
count(p.*)
from product p
inner join type t on t.id = p.type_id
group by t.name
having count(p.*) < 10
--Вывести все продукты и их тип.
select
p.name,
t.name
from product p
inner join type t on t.id = p.type_id