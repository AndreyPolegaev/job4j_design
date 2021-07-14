-- one-to-many 
-- Одно издательство может иметь много книг 
-- Каждая книга имеет только одно издательство 

CREATE TABLE publisher
(
	id serial PRIMARY KEY, 
	name varchar(128) NOT NULL 
); 

CREATE TABLE books 
(
	id serial PRIMARY KEY, 
	title text NOT NULL, 
	fk_publisher_id int REFERENCES publisher(id)
); 

INSERT INTO books (title, fk_publisher_id)
VALUES 
('Java for professionals', 1),
('SQL for beginners', 3), 
('Spring in action', 2), 
('Java for everyone', 1);


INSERT INTO publisher(name)
VALUES 
('Oracle'),
('Orelly'),
('Head First'); 

