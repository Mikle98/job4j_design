create table books(
	id serial primary key,
	name varchar(255),
	author varchar(255) references author(name)
);

create table author(
	name varchar(255) primary key,
	age int
)

insert into author (name, age)
values ('Pushkin', 37);

insert into author (name, age)
values ('Lermontov', 26);

insert into books (name, author)
values ('Mciri', 'Lermontov');

insert into books (name, author)
values ('Demon', 'Lermontov');

insert into books (name, author)
values ('Mciri', 'Pushkin');

insert into books (name, author)
values ('Onegin', 'Pushkin');

insert into books (name, author)
values ('Dubrovski', 'Pushkin');

select
a.name as name_author,
b.name as name_book
from books b
inner join author a on a.name = b.author

select
a.name as name_author,
b.name as name_book
from books b
inner join author a on a.name = b.author
where a.name like 'Pushkin'

select
a.name as name_author,
b.name as name_book
from books b
inner join author a on b.name = 'Mciri'