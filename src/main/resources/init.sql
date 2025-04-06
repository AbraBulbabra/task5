CREATE SCHEMA IF NOT EXISTS test;
USE test;

CREATE TABLE students (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), surname VARCHAR(100),
email_student VARCHAR(255) UNIQUE);

INSERT INTO students(name, surname, email_student) VALUES ('Piter','Trump', 'AQA@gmail.com');
INSERT INTO students(name, surname, email_student) VALUES ('Myshel','Smyt', 'mol@mail.ru');
INSERT INTO students(name, surname, email_student) VALUES ('Oleg','Touranov', 'abra@list.by');