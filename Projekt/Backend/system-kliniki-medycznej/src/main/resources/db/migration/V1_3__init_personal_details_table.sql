CREATE table personal_details
(
    id           SERIAL      NOT NULL PRIMARY KEY,
    pesel        varchar(128) UNIQUE,
    name         varchar(64) NOT NULL,
    surname      varchar(64) NOT NULL,
    birth_date   date,
    address_id   INT,
    gender       varchar(32),
    phone_number varchar(64) UNIQUE,
    CONSTRAINT fk_personal_details_address FOREIGN KEY (address_id) REFERENCES address (id)
)