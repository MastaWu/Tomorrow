CREATE DATABASE TomorrowDB;

CREATE TABLE User(
	id int NOT NULL AUTO_INCREMENT,
    username varchar(30) NOT NULL,
    password varchar(30) NOT NULL,
    email varchar(30),
    PRIMARY KEY(id));