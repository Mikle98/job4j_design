create table owner(
	id serial primary key,
	name varchar(255),
	number_auto varchar(255) references car(number) unique
);