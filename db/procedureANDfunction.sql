create
or replace procedure deleteRowId(i_id integer)
language 'plpgsql'
as $$
	begin
		delete from products
			where id = i_id;
	end;
$$;

create
or replace function deleteRowZeroCount()
returns void
language 'plpgsql'
as $$
	begin
		delete from products
			where count = 0;
	end;
$$;

DROP FUNCTION deleterowzerocount()

insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);

insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 0, 115);

insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 0, 115);

insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 0, 115);

select * from products;

call deleteRowId(13);

select deleteRowZeroCount();