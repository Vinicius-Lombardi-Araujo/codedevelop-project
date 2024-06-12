CREATE TABLE USERS (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    name varchar(100) not null,
    email varchar(50) unique not null,
    password varchar(100) not null,
    type varchar(20) not null
);