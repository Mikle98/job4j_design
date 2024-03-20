create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);
----Триггер должен срабатывать после вставки данных, для любого товара и просто насчитывать налог на товар (нужно прибавить налог к цене товара). Действовать он должен не на каждый ряд, а на запрос (statement уровень)
create
or replace function nds()
	returns trigger as
$$
	begin
		update products
		set price = price + price * 0.2;
		return new;
	end;
$$
language 'plpgsql';

create trigger nds_trigger
	after insert
	on products
	referencing new table as
		inserted
	for each statement
	execute procedure nds();
--Триггер должен срабатывать до вставки данных и насчитывать налог на товар (нужно прибавить налог к цене товара). Здесь используем row уровень.
create trigger nds_before_add_trigger
	before insert
	on products
	for each row
	execute procedure nds();
--Нужно написать триггер на row уровне, который сразу после вставки продукта в таблицу products, будет заносить имя, цену и текущую дату в таблицу history_of_price.
create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create
or replace function fullTable()
	returns trigger as
$$
	begin
		insert into history_of_price(name, price, date)
		values ((select name from inserted),
			   (select price from inserted),
			   now());
		return new;
	end
$$
language 'plpgsql'

create trigger fullTable_trigger
	after insert
	on products
	referencing new table as
		inserted
	for each row
	execute procedure fullTable();