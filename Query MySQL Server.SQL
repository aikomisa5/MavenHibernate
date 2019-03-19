GRANT ALL PRIVILEGES ON *.* TO 'aikomisa5'@'localhost' IDENTIFIED BY 'aikomisa5';
CREATE DATABASE basePrueba;
USE basePrueba;
CREATE TABLE personas (idPersona bigint, nombre varchar(45), apellido varchar(45), edad int, mail varchar(45), dni varchar(45), activo bool);
