--1
CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

insert into customers (first_name, last_name, age, country)
values('Иван', 'Иванов', 10, 'Россия');
insert into customers (first_name, last_name, age, country)
values('Петр', 'Петров', 20, 'Россия');
insert into customers (first_name, last_name, age, country)
values('Алексей', 'Козлов', 30, 'Россия');

--Выполните запрос, который вернет список клиентов, возраст которых является минимальным.
select
*
from customers
where age = (select min(age) from customers)
--2
CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

insert into orders (amount, customer_id)
values (1, 1);
insert into orders (amount, customer_id)
values (2, 1);
--Необходимо выполнить запрос, который вернет список пользователей, которые еще не выполнили ни одного заказа. Используйте подзапрос, для реализации Вам понадобится NOT IN.
select
*
from customers
where id not in (select customer_id from orders)
