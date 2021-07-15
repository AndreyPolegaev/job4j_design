--users - role = many-to-one +
--role - rules = many-to-many +
--item - users = many-to-one +
--item - comments = one-to-many + 
--item - attachs = one-to-many + 
--item - category = many-to-one + 
--item - state = many-to-one

CREATE TABLE role
(
    id serial PRIMARY KEY,
    name varchar(64)
);

CREATE TABLE users
(
	id serial PRIMARY KEY, 
	name varchar(64),
	fk_role_id int REFERENCES role(id)
);

CREATE TABLE rules
(
	id serial PRIMARY KEY, 
	name varchar(64)
); 

CREATE TABLE role_rules
(
	id serial PRIMARY KEY, 
	fk_role_id int REFERENCES role(id),
	fk_rules_id int REFERENCES rules(id)
);

CREATE TABLE category
(
	id serial PRIMARY KEY, 
	name varchar(64)
);

CREATE TABLE state
(
	id serial PRIMARY KEY, 
	name varchar(64)
); 

CREATE TABLE item
(
	id serial PRIMARY KEY, 
	name varchar(64),
	fk_users_id int REFERENCES users(id),
    fk_category_id int REFERENCES category(id),
    fk_state_id int REFERENCES state(id)
); 

CREATE TABLE comments
(
	id serial PRIMARY KEY, 
	name varchar(64),
	fk_item_id int REFERENCES item(id)
); 

CREATE TABLE attachs
(
	id serial PRIMARY KEY, 
	name varchar(64), 
	fk_item_id int REFERENCES item(id)
); 



 



