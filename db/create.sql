create table roles(
	name varchar(255) primary key
);

create table rules(
	name varchar(255) primary key
);

create table states(
	id serial primary key
);

create table categories(
	id serial primary key
);

create table users(
	id serial primary key,
	role varchar(255) references roles(name)
);

create table items(
	id serial primary key,
	id_user int references users(id),
	category int references categories(id),
	state int references states(id)
);

create table comments(
	id serial primary key,
	item int references items(id)
);

create table attachs(
	id serial primary key,
	item int references items(id)
);

create table roles_rules(
	id serial primary key,
	role varchar(255) references roles(name),
	rule varchar(255) references rules(name)
);