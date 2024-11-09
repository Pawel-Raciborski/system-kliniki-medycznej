CREATE TABLE doctor
(
    id                  SERIAL NOT NULL PRIMARY KEY,
    account_id          INT    NOT NULL,
    personal_details_id INT    NOT NULL,
    description         text,
    pwz_number          VARCHAR(16),
    profile_image_path varchar(512),
    date_of_employment  DATE
)