CREATE table patient
(
    id                   SERIAL NOT NULL PRIMARY KEY,
    account_id           INT,
    date_of_registration timestamp,
    parent_pesel         varchar(128), -- if patient is not an adult
    personal_details_id  INT    NOT NULL,
    CONSTRAINT fk_patient_user FOREIGN KEY (account_id) REFERENCES account (id),
    CONSTRAINT fk_patient_personal_details FOREIGN KEY (personal_details_id) REFERENCES personal_details (id)
);