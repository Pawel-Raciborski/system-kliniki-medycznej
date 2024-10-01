-- INIT DOCTOR CALENDAR TABLE
create table doctor_calendar
(
    id         SERIAL NOT NULL PRIMARY KEY,
    doctor_id  INT    NOT NULL,
    created_at timestamp,
    CONSTRAINT fk_doctor_calendar FOREIGN KEY (doctor_id) REFERENCES doctor (id)
)