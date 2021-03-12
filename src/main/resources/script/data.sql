
CREATE USER IF NOT EXISTS 'user1'@'localhost' IDENTIFIED BY '123';
 
GRANT ALL PRIVILEGES ON *.* TO 'user1'@'localhost' WITH GRANT OPTION;

CREATE DATABASE IF NOT EXISTS `userdb`;

USE userdb;

CREATE TABLE IF NOT EXISTS `users` (
 id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY, date_of_birth datetime(6), date_of_joining datetime(6) UNIQUE, email varchar(50) NOT NULL, first_name varchar(50), is_delete tinyint(1) NOT NULL, last_name varchar(50), pin_code varchar(50) );

INSERT INTO `users`(id,date_of_birth, date_of_joining, email, first_name, last_name,is_delete, pin_code) 
SELECT 1, '1962-10-11 05:30:00.000000', '1998-10-17 05:30:00.000000','user1@gmail.com','user1','user1',0,'680301' 
WHERE NOT EXISTS( SELECT id FROM users WHERE email = 'user1@gmail.com' );

INSERT INTO `users`(id,date_of_birth, date_of_joining, email, first_name, last_name,is_delete, pin_code) 
SELECT 2, '1988-10-20 05:30:00.000000', '2000-10-17 05:30:00.000000','user2@gmail.com','user2','user2',0,'680301' 
WHERE NOT EXISTS( SELECT id FROM users WHERE email = 'user2@gmail.com' );

INSERT INTO `users`(id,date_of_birth, date_of_joining, email, first_name, last_name,is_delete, pin_code) 
SELECT 3, '1998-10-20 05:30:00.000000', '2010-10-17 05:30:00.000000','user3@gmail.com','user3','user3',0,'680301' 
WHERE NOT EXISTS( SELECT id FROM users WHERE email = 'user3@gmail.com' );

INSERT INTO `users`(id,date_of_birth, date_of_joining, email, first_name, last_name,is_delete, pin_code) 
SELECT 4, '1988-10-20 05:30:00.000000', '2002-10-17 05:30:00.000000','user4@gmail.com','user4','user4',0,'680301' 
WHERE NOT EXISTS( SELECT id FROM users WHERE email = 'user4@gmail.com' );

INSERT INTO `users`(id,date_of_birth, date_of_joining, email, first_name, last_name,is_delete, pin_code) 
SELECT 5, '1996-10-20 05:30:00.000000', '2018-10-17 05:30:00.000000','user5@gmail.com','user5','user5',0,'680301' 
WHERE NOT EXISTS( SELECT id FROM users WHERE email = 'user5@gmail.com' );

INSERT INTO `users`(id,date_of_birth, date_of_joining, email, first_name, last_name,is_delete, pin_code) 
SELECT 6, '1996-10-20 05:30:00.000000', '2018-08-15 05:30:00.000000','user6@gmail.com','user6','user6',0,'680301' 
WHERE NOT EXISTS( SELECT id FROM users WHERE email = 'user6@gmail.com' );


INSERT INTO `users`(id,date_of_birth, date_of_joining, email, first_name, last_name,is_delete, pin_code) 
SELECT 7, '1996-10-20 05:30:00.000000', '2018-11-25 05:30:00.000000','user7@gmail.com','user7','user7',0,'680301' 
WHERE NOT EXISTS( SELECT id FROM users WHERE email = 'user7@gmail.com' );
