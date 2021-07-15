create table author(
    id serial primary key,
    name varchar(32),
	surname varchar(32)
);

create table song(
    id serial primary key,
    name varchar(255),
    author_id int references author(id)
);

insert into author(name, surname) values ('Till', 'Lindeman'); 
insert into author(name, surname) values ('James', 'Hetfild'); 
insert into author(name, surname) values ('Ozzy', 'Ozborn'); 

insert into song(name, author_id) values ('Home sweet home', 1);
insert into song(name, author_id) values ('Do hast', 1);
insert into song(name, author_id) values ('master of puppets', 2);
insert into song(name, author_id) values ('seek and destroy', 2);
insert into song(name, author_id) values ('benzin', 2);
insert into song(name, author_id) values ('Rain', 3);
insert into song(name, author_id) values ('Paranoid', 3);

insert into song(name) values ('some song1');
insert into song(name) values ('some song2');
insert into song(name) values ('some song3');

select *from song join author p on song.author_id = p.id;
select pp.name, pp.surname, p.name from author as pp join song as p on p.author_id = pp.id;
select pp.name as Имя, pp.surname as Фамилия, p.name as Название from author as pp join song as p on p.author_id = pp.id;