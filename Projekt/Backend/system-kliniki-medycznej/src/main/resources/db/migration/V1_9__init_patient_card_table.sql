create table patient_card
(
    id                      uuid NOT NULL PRIMARY KEY,
    patient_id              INT  NOT NULL UNIQUE,
    created_date_time       timestamp,
    last_visit_date_time    timestamp, -- on each visit this date is updated
    interview_id            INT UNIQUE,
    patient_data_files_path text,
    patient_details_id      INT UNIQUE,
    CONSTRAINT fk_patient_card_patient FOREIGN KEY (patient_id) REFERENCES patient (id),
    CONSTRAINT fk_patient_card_interview FOREIGN KEY (interview_id) REFERENCES interview (id),
    CONSTRAINT fk_patient_card_patient_details FOREIGN KEY (patient_details_id) REFERENCES patient_details (id)
)