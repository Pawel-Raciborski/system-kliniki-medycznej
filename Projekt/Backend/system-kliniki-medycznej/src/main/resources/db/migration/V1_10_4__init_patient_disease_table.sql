create table patient_disease
(
    id              SERIAL       NOT NULL PRIMARY KEY,
    patient_card_id uuid         NOT NULL,
    disease_id    int not null,
    description     text         NOT NULL,
    detection_date  date         NOT NULL,
    cure_status     varchar(64)  NOT NULL,
    finish_date     date,
    CONSTRAINT fk_patient_disease_patient_card FOREIGN KEY (patient_card_id) REFERENCES patient_card (id),
    CONSTRAINT fk_patient_disease_disease FOREIGN KEY (disease_id) REFERENCES disease (id)
)