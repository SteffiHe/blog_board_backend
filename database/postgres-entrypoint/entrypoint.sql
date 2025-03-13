-- Create the database (only works if executed by the `postgres` user)
-- CREATE DATABASE test;

-- Connect to the test databasese
-- \c test;


CREATE TABLE public.user
(
    -- id SERIAL
    id SERIAL PRIMARY key NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) DEFAULT NULL,
    birthday Date  DEFAULT NULL,
    email VARCHAR(50) DEFAULT NULL,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO public.user (USERNAME, PASSWORD, BIRTHDAY, EMAIL) VALUES
('lucy', '123', '2023-12-06', 'lucy@test.com'),
('thomas', '123', '2024-01-01', 'thomas@test.com');
