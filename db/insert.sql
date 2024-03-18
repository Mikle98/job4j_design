insert into roles(name) values ('Admin');
insert into rules(name) values ('FULL_ACCESS');
insert into states(id) values (1);
insert into categories(id) values (1);
insert into users(role) values ('Admin');
insert into items(id_user, category, state) values (1, 1, 1);
insert into comments(item) values (1);
insert into attachs(item) values (1);
insert into roles_rules(role, rule) values('Admin', 'FULL_ACCESS');