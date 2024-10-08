drop database if exists touristattraction; 
create database touristattraction;
use touristattraction; 

create table city(name varChar(255), location int,
city_id int AUTO_INCREMENT PRIMARY KEY);

create table tag(tag_name varChar(255) NOT NULL, 
tag_id int AUTO_INCREMENT PRIMARY KEY);

create table touristattraction(tname varChar(255) NOT NULL, description varChar(255), pris int,
tourist_id int AUTO_INCREMENT PRIMARY KEY, city_id int,
FOREIGN KEY (city_id) REFERENCES city(city_id));

create table touristattraction_tag(
tag_id int, tourist_id int,
PRIMARY KEY (tag_id, tourist_id),
FOREIGN KEY (tag_id) REFERENCES tag(tag_id),
FOREIGN KEY (tourist_id) REFERENCES touristattraction(tourist_id));

INSERT INTO city (name, location)
VALUES 
('Roskilde', 4000),
('Copenhagen', 2300),
('Norrebro', 2200);

INSERT INTO tag (tag_name)
VALUES ('Forlystelse'), 
('Ballon'),
('Natur');

INSERT INTO touristattraction(tname, description, pris, city_id)
VALUES ('Tivoli', 'A amusement park', 150,2),
('Zoo', 'A wildlife park, home to a wide variety of animals from around the world.', 170, 3);

select * from city;