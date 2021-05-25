create table users (
    id int NOT NULL,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    subscribe VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL
);

create table music (
    id int NOT NULL,
    author VARCHAR(255) NOT NULL,
    min VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    year int NOT NULL,
);