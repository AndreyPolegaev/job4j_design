-- many-to-many

CREATE TABLE cinema
(
	id serial PRIMARY KEY, 
	name varchar(128) NOT NULL 
); 

CREATE TABLE muvie 
(
	id serial PRIMARY KEY, 
	title text NOT NULL 	
);

CREATE TABLE cinema_movies
(
	id serial PRIMARY KEY,
	cinema_id int REFERENCES cinema(id),
	movie_id int REFERENCES muvie(id)
);

INSERT INTO cinema(name) 
VALUES 
('Karo Film'), 
('Formula Kino'), 
('Super Kino'); 

INSERT INTO muvie (title)
VALUES 
('King Kong'), 
('Terminatir'), 
('Harry Potter'); 

INSERT INTO cinema_movies (cinema_id, movie_id)
VALUES
(1, 2),
(1, 3),
(2, 1),
(2, 2), 
(2, 3), 
(3, 1);

