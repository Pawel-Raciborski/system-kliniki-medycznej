create table patient_disease
(
    id                  SERIAL       NOT NULL PRIMARY KEY ,
    patient_card_id     uuid         NOT NULL,
    disease_name        varchar(256) NOT NULL,
    description         text         NOT NULL,
    detection_date_time timestamp    NOT NULL,
    cure_status         varchar(64)  NOT NULL,
    finish_date         timestamp,
    CONSTRAINT fk_patient_disease_patient_card FOREIGN KEY (patient_card_id) REFERENCES patient_card (id)
)