create table body
(
    id   serial primary key,
    name text
);

create table engine
(
    id   serial primary key,
    name text
);

create table transmission
(
    id   serial primary key,
    name text
);

create table car
(
    id              serial primary key,
    name            text,
    body_id         int references body (id),
    engine_id       int references engine (id),
    transmission_id int references transmission (id)
);

insert into body (name)
values ('sedan'),
       ('hatchback'),
       ('universal');

insert into engine (name)
values ('1.5'),
       ('1.8'),
       ('2.0'),
       ('2.5');

insert into transmission (name)
values ('mechanic'),
       ('auto'),
       ('robot');

insert into car (name, body_id, engine_id, transmission_id)
values ('car1', 1, 3, 2),
       ('car2', 2, 2, 1),
       ('car3', 1, 1, 2);


--Вывести список всех машин и все привязанные к ним детали.
select c.name as "Автомоиль", b.name as "Кузов", e.name as "Обьем двигателя", t.name as "Трансмиссия"
from car c
         join body b on c.body_id = b.id
         join engine e on c.engine_id = e.id
         join transmission t on c.transmission_id = t.id;

-- Детали, которые не используются НИ в одной машине, кузова, двигатели, коробки передач.
select *from body b left join car c on b.id = c.body_id where c.id isnull;
select *from engine e left join car c on e.id = c.body_id where c.id isnull;
select *from transmission t left join car c on t.id = c.body_id where c.id isnull;


