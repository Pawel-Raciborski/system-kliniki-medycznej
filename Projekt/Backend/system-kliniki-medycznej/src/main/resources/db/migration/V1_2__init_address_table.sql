CREATE TABLE address
(
    id               SERIAL NOT NULL PRIMARY KEY,
    street           varchar(64),
    apartment_number varchar(32),
    postal_code      varchar(64),
    city_id          INT    NOT NULL,
    CONSTRAINT fk_address_city FOREIGN KEY (city_id) REFERENCES city (id)
)