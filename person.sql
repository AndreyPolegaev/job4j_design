CREATE TABLE person 
id serial primary key, 
name varchar(255), 
surname text, 
passport integer, 
birthday date 
)
INSERT into person (name, surname, passport, birthday) values ('Andrey', 'Polegaev', 123123, '1990-07-10'); 
UPDATE person set passport = 7777777; 
DELETE from person; 
SELECT *from person;

