CREATE TABLE account
(
    id               SERIAL       NOT NULL PRIMARY KEY,
    username         varchar(64)  NOT NULL,
    password         varchar(256) NOT NULL,
    email            varchar(64)  NOT NULL,
    date_time_of_creation timestamp
)