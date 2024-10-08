CREATE table cure
(
    id          SERIAL       NOT NULL PRIMARY KEY,
    name        varchar(256) NOT NULL,
    description text         NOT NULL
);