DROP TABLE IF EXISTS user_details;

CREATE TABLE user_details(
userId int not null primary key auto_increment,
firstName varchar(255) not null,
lastName varchar(255) not null,
gender varchar(255) not null,
age int(20) not null
)