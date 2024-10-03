CREATE TABLE doctor
(
    id                  SERIAL NOT NULL PRIMARY KEY,
    account_id          INT    NOT NULL,
    personal_details_id INT    NOT NULL,
    description         text,
    calendar_id         INT,
    pwz_number          VARCHAR(16),
    date_of_employment  DATE,
    CONSTRAINT fk_doctor_personal_details FOREIGN KEY (personal_details_id) REFERENCES personal_details (id),
    CONSTRAINT fk_doctor_user FOREIGN KEY (account_id) REFERENCES account (id),
    CONSTRAINT fk_doctor_calendar FOREIGN KEY (calendar_id) REFERENCES doctor_calendar (id)
)