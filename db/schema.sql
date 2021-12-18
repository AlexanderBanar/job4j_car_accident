create database auto_crash;

create table accident (
                          id serial primary key,
                          name varchar,
                          address varchar,
                          number varchar,
                          description varchar,
                          status varchar
);

insert into accident(name, address, number, description, status) VALUES
('Иван', 'Москва', 'Д503ДД', 'неправильная парковка', 'Принята');

insert into accident(name, address, number, description, status) VALUES
('Сергей', 'Казань', 'Р404РР', 'затонирована задняя полусфера', 'Отклонена');

insert into accident(name, address, number, description, status) VALUES
('Антон', 'Воронеж', 'Н111НН', 'отсутствие шипованных шин', 'Завершена');


create table rules (
                       id serial primary key,
                       name varchar
);

insert into rules(name) values ('Статья. 1');
insert into rules(name) values ('Статья. 2');
insert into rules(name) values ('Статья. 3');

create table types (
                       id serial primary key,
                       name varchar
);

insert into types(name) values ('Две машины');
insert into types(name) values ('Машина и человек');
insert into types(name) values ('Машина и велосипед');

alter table accident add column type_id int references types(id);

update accident set type_id = 1;

create table accidents_rules (
                                 id serial primary key,
                                 accident_id int references accident(id),
                                 rule_id int references rules(id)
);

insert into accidents_rules(accident_id, rule_id) VALUES (1, 1);
insert into accidents_rules(accident_id, rule_id) VALUES (1, 3);
insert into accidents_rules(accident_id, rule_id) VALUES (2, 2);
insert into accidents_rules(accident_id, rule_id) VALUES (3, 1);
insert into accidents_rules(accident_id, rule_id) VALUES (3, 2);
insert into accidents_rules(accident_id, rule_id) VALUES (3, 3);
insert into accidents_rules(accident_id, rule_id) VALUES (4, 1);
insert into accidents_rules(accident_id, rule_id) VALUES (5, 3);