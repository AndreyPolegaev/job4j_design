--product(id, name, type_id, expired_date, price)
--type(id, name)

create table type( 
id serial primary key, 
name varchar(255)
); 

create table product(
id serial primary key, 
name varchar(255), 
expired_date date, 
price float,
type_id int references type(id)
);

insert into type (name) values 
('СЫР'), 
('МОЛОКО'), 
('СОК'), 
('МОРОЖЕНОЕ');

insert into product (name, expired_date, price, type_id) 
values 
('сыр ламбер', '2021-05-10', 100.0, 1), 
('сыр тильзитер', '2021-05-10', 120.0, 1),
('молоко М', '2021-05-15', 100.0, 2), 
('молоко Домик в деревне', '2021-05-12', 100.0, 2), 
('сок J7', '2021-10-10', 50.0, 3), 
('сок Я', '2021-11-11', 60.0, 3),
('мороженое замороженное', '2021-11-11', 10.0, 4),
('мороженое размороженное', '2021-11-11', 12.0, 4),
('любимое мороженое 1', '2021-11-11', 10.0, 4),
('любимое мороженое 2', '2021-11-11', 10.0, 4);


select *from product; 

--все продукты с типом СЫР
select *from product where type_id = 1;

select * from product inner join type on product.type_id=type.id
where type.name='СЫР';


-- у кого в имени есть слово "мороженое"
select *FROM product WHERE name LIKE '%мороженое%';

-- истек срок годности
select *from product where expired_date < current_date;

-- самый дорогой продукт 
select name from product where price = (select max(price) from product);
select name from product order by price desc limit 1;

-- кол-во продуктов каждого типа
select type.name as Имя_типа, count(type_id) as Количество
from product inner join type on product.type_id = type.id
group by type.name;

-- получить все с типом СЫР И МОЛОКО
select *from product join type on type.id = product.type_id 
where type.name = 'СЫР' or type.name = 'МОЛОКО';

--Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select t.name from type t inner join product p on p.type_id = t.id
group by t.name
having count(p.type_id) < 10;

--Вывести все продукты и их тип 
select s.name, ss.name from product s join type ss on ss.id = s.type_id;
