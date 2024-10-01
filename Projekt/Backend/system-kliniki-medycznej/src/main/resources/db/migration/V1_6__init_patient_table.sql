CREATE table patient
(
    id                   SERIAL NOT NULL PRIMARY KEY,
    user_id              INT,
    date_of_registration timestamp,
    parent_pesel         varchar(128) UNIQUE, -- if patient is not an adult
    personal_details_id  INT    NOT NULL,
    CONSTRAINT fk_patient_user FOREIGN KEY (user_id) REFERENCES user_table (id),
    CONSTRAINT fk_patient_personal_details FOREIGN KEY (personal_details_id) REFERENCES personal_details (id)
);