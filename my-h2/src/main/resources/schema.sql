DROP TABLE IF EXISTS employees;

CREATE TABLE employees(
id int not null primary key auto_increment,
first_name varchar(50) not null,
last_name varchar(50) not null,
address varchar(255) not null,
joining_date timestamp default(CURRENT_TIME)
);