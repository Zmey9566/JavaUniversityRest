--liquibase formatted sql
--changeset Alexander:1


DROP TABLE IF EXISTS admin;
-- DROP TABLE IF EXISTS mentor_student;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS mentor;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS role;

-- Создание таблицы role

CREATE TABLE role
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(40)
);

-- Создание таблицы admin

CREATE TABLE IF NOT EXISTS admin
(
    id     SERIAL PRIMARY KEY,
    family varchar(25),
    name   varchar(25),
    email varchar(50),
    role_id INT references role (id) ON DELETE CASCADE ON UPDATE CASCADE,
    password varchar(60)
    );

-- Создание таблицы mentor

CREATE TABLE IF NOT EXISTS mentor
(
    id     SERIAL PRIMARY KEY,
    family varchar(25),
    name   varchar(25),
    email varchar(50),
    role_id INT references role (id) ON DELETE CASCADE ON UPDATE CASCADE,
    password varchar(60)
    );

-- Создание таблицы student

CREATE TABLE IF NOT EXISTS student
(
    id     SERIAL PRIMARY KEY,
    family varchar(25),
    name   varchar(25),
    email varchar(50),
--     level varchar(50),
    role_id INT references role (id) ON DELETE CASCADE ON UPDATE CASCADE,
    password varchar(60)
    );

-- Создание таблицы users

CREATE TABLE IF NOT EXISTS users
(
    id     SERIAL PRIMARY KEY,
    email varchar(50),
    role_id INT references role (id) ON DELETE CASCADE ON UPDATE CASCADE,
    model_id INT,
    password varchar(60)
    );
