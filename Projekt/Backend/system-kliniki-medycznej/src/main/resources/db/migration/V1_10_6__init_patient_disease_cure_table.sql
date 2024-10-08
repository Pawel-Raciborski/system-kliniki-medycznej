create table patient_disease_medicine
(
    id                 SERIAL      NOT NULL PRIMARY KEY,
    patient_disease_id INT         NOT NULL,
    medicine_id            INT         NOT NULL,
    medicine_dosage        varchar(64) NOT NULL,
    notes              text,
    medicine_update_date   date,
    CONSTRAINT fk_patient_disease_medicine_patient_disease FOREIGN KEY (patient_disease_id) REFERENCES patient_disease (id),
    CONSTRAINT fk_patient_disease_medicine_cure FOREIGN KEY (medicine_id) REFERENCES medicine (id)
);