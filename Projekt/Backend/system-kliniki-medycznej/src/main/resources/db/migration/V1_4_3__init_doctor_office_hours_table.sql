CREATE TABLE doctor_office_hours
(
    id                  SERIAL      NOT NULL PRIMARY KEY,
    doctor_id           INT         NOT NULL,
    day                 varchar(32) NOT NULL,
    start_time          TIME        NOT NULL,
    end_time            TIME        NOT NULL,
    duration_in_minutes INT,
    modified_date       date
);