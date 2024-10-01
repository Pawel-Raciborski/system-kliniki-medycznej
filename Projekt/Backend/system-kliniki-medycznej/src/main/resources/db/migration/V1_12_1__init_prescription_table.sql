create table prescription
(
    id            SERIAL NOT NULL PRIMARY KEY,
    doctor_id     INT    NOT NULL,
    date_of_issue date   NOT NULL,
    description   text,
    CONSTRAINT fk_prescription_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (id)
)