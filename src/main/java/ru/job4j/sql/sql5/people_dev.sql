create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (name, price) values 
('iphone', 1250.0), 
('ipad', 900.5), 
('mackBook', 2000.0); 

insert into people (name) values 
('Андрей'),
('Владимир'), 
('Николай'); 

insert into devices_people (people_id, device_id) values 
(1, 1), -- у Андрея 3 айфона и 1 macBook 
(1, 1), 
(1, 1), 
(1, 3), 
(2, 1), -- у Владимира 1 айфон и 1 ipad
(2, 2), 
(3, 1), --  у Николая 1 айфон 1 Ipad и 1 macBook 
(3, 2), 
(3, 3); 

select avg(price) from devices; -- средняя цена девайсов

select s.people_id, avg(ss.price) -- средняя цена девайсов по каждому человеку
from devices_people s join devices ss on s.device_id = ss.id
group by s.people_id;

select s.people_id, avg(ss.price) -- средняя цена девайсов по каждому человеку > 5000
from devices_people s join devices ss on s.device_id = ss.id
group by s.people_id
having avg(ss.price) > 5000;









