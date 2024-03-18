create table brand_car(
	brand varchar(255) primary key,
	car_number varchar(255) references car(number),
	owner_id int references owner(id)
);