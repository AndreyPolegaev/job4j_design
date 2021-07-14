INSERT INTO role(name) VALUES ('Programmer'); 
INSERT INTO users(name) VALUES ('Andrey'); 
INSERT INTO rules(name) VALUES ('Write good java code');
INSERT INTO category(name) VALUES ('unknown category'); 
INSERT INTO state(name) VALUES ('unknown state'); 
INSERT INTO item(name, fk_users_id, fk_category_id, fk_state_id) VALUES ('CreateProgram', 1, 1, 1); 
INSERT INTO attachs(name, fk_item_id) VALUES ('some file', 1); 
INSERT INTO comments(name, fk_item_id) VALUES ('some comment', 1); 
INSERT INTO role_rules(fk_role_id, fk_rules_id) VALUES (1, 1); 








