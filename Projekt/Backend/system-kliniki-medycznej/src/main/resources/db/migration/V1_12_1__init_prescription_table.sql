create table prescription
(
    id              uuid NOT NULL PRIMARY KEY,
    doctor_id       INT    NOT NULL,
    patient_id      INT    NOT NULL,
    expiration_date date   NOT NULL,
    created_date    date   NOT NULL,
    description     text,
    CONSTRAINT fk_prescription_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (id),
    CONSTRAINT fk_prescription_patient FOREIGN KEY (patient_id) REFERENCES patient (id)
)