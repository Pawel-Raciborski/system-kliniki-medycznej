CREATE TABLE doctor_specialization
(
    id            SERIAL      NOT NULL PRIMARY KEY,
    name          varchar(64) NOT NULL,
    description   TEXT,
    realized_date DATE        NOT NULL,
    doctor_id     INT         NOT NULL,
    CONSTRAINT fk_doctor_specialization_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (id)
)