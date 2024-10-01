CREATE TABLE city
(
    id   SERIAL NOT NULL PRIMARY KEY,
    name varchar(128) UNIQUE
)