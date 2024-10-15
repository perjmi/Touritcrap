drop database if exists touristattraction; 
create database if not exists touristattraction;
use touristattraction; 

create table city(name varChar(255),
cityId int AUTO_INCREMENT PRIMARY KEY);

create table tag(tag_name varChar(255) NOT NULL, 
tag_id int AUTO_INCREMENT PRIMARY KEY);

create table touristattraction(name varChar(255) NOT NULL, description varChar(255), prisDollar int,
tourist_id int AUTO_INCREMENT PRIMARY KEY, cityId int,
FOREIGN KEY (cityId) REFERENCES city(cityId));

create table touristattraction_tag(
tag_id int, tourist_id int,
PRIMARY KEY (tag_id, tourist_id),
FOREIGN KEY (tag_id) REFERENCES tag(tag_id),
FOREIGN KEY (tourist_id) REFERENCES touristattraction(tourist_id));

INSERT INTO city(name)
VALUES 
('Roskilde'),
('Copenhagen'),
('Norrebro');

INSERT INTO tag (tag_name)
VALUES ('Forlystelse'), 
('Ballon'),
('Natur');

INSERT INTO touristattraction(name, description, prisDollar, cityId)
VALUES('Tivoli', 'an amusement park', 10, 1);



