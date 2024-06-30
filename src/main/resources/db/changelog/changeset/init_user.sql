CREATE SCHEMA IF NOT EXISTS coworking;

CREATE TABLE IF NOT EXISTS coworking."users-liquibase"
(
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

INSERT INTO coworking."users-liquibase" (username, email, password) VALUES
('admin', 'admin@test.com', 'pw1'),
('user', 'user@test.com', 'pw2');