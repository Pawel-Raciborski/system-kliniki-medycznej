create table doctor_calendar
(
    id            SERIAL NOT NULL PRIMARY KEY,
    created_at    timestamp,
    last_modified timestamp,
    doctor_id     INT NOT NULL
);