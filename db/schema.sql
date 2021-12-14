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