--departments и emploees. Отношение one-to-many. В таблицах только поле name.
--Даны две сущности, представленные в таблицах departments и emploees. Отношение one-to-many. 
--В таблицах только поле name.

1. Создать таблицы и заполнить их начальными данными

2. Выполнить запросы с left, rigth, full, cross соединениями

3. Используя left join найти департаменты, у которых нет работников

4. Используя left и right join написать запросы, которые давали бы одинаковый результат. 


create table departments (
	id serial primary key,
	name varchar(255)
);

create table employees (
	id serial primary key,
	name varchar(255), 
	departments_id int references departments(id)
);

insert into departments (name) values 
('dep 1'), 
('dep 2'),
('dep 3');

insert into employees (name, departments_id) values 
('Ivan', 1), 
('Petr', 1), 
('Stas', 1),
('Olga', null), 

('Andrey', 2),
('Vladimir', 2),
('Sergey', null),
('Nikolay', 2);

--left join
select *from employees e left join departments d on e.departments_id = d.id;

--right join
select *from employees e right join departments d on e.departments_id = d.id;

-- full join
select *from employees e full join departments d on e.departments_id = d.id;

--cross join 
select *from employees e cross join departments d;

-- сотрудники вне департаментов
select *from employees e left join departments d on e.departments_id = d.id where d.id isnull;

--департаменты без работников 
select *from departments d left join employees e on e.departments_id = d.id where e.id isnull;

--left, right с одинаковым результатом 
select *from employees e left join departments d on e.departments_id = d.id;
select *from departments d right join employees e on e.departments_id = d.id;


--Создать таблицу teens с атрибутами name, gender и заполнить ее. 
--Используя cross join составить все возможные разнополые пары


create table teens (
	id serial primary key,
	name varchar(255), 
	gender varchar(6)
);

insert into teens (name, gender) values 
('Katia', 'female'), 
('Vika', 'female'), 
('Volodia', 'male'), 
('Stas', 'male'); 

select t1.name, t2.gender from teens t1 cross join teens t2; 




