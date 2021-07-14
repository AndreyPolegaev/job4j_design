-- one-to-one

CREATE TABLE car
(
	id serial PRIMARY KEY, 
	name varchar(128) NOT NULL,
	name_engine varchar(64) NOT NULL
); 

CREATE TABLE numbercar
(
	id serial PRIMARY KEY, 
	name varchar(32) NOT NULL
);

CREATE TABLE car_numbercar
(
	id serial PRIMARY KEY,
	car_id int REFERENCES car(id) unique, 
	numbercar_id int REFERENCES numbercar(id) unique
);



INSERT INTO car(name, name_engine) 
VALUES 
('BMW 5', '12345'), 
('Ford Focus', '3333'), 
('Mersedes SLS', '87654'); 

INSERT INTO numbercar (name)
VALUES 
('A111EE77'), 
('K707TM32'), 
('X010КА99'); 

INSERT INTO car_numbercar (car_id, numbercar_id)
VALUES
(1, 3),
(2, 2),
(3, 1);

