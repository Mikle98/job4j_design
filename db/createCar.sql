create table car(
	number varchar(255) primary key,
	type_transport int references transport(id)
);