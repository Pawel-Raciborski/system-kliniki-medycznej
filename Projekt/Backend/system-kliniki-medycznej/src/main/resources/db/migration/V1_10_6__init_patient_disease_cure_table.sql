create table patient_disease_cure
(
    id                 SERIAL      NOT NULL PRIMARY KEY,
    patient_disease_id INT         NOT NULL,
    cure_id            INT         NOT NULL,
    cure_dosage        varchar(64) NOT NULL,
    notes              text,
    cure_update_date   date,
    CONSTRAINT fk_patient_disease_cure_patient_disease FOREIGN KEY (patient_disease_id) REFERENCES patient_disease (id),
    CONSTRAINT fk_patient_disease_cure_cure FOREIGN KEY (cure_id) REFERENCES cure (id)
);