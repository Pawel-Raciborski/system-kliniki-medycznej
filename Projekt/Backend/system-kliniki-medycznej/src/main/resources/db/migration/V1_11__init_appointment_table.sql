create table appointment
(
    id           uuid        not null PRIMARY KEY,
    doctor_id    INT         NOT NULL,
    patient_card_id uuid, -- moze być pierwsza wizyta, więc nie ma zalozonej karty pacjenta
    visit_date   timestamp   NOT NULL,
    finish_date  timestamp,
    status       varchar(64) NOT NULL,
    diagnosis    text,
    CONSTRAINT fk_appointment_doctor FOREIGN KEY (doctor_id) REFERENCES doctor(id),
    CONSTRAINT fk_appointment_patient_card FOREIGN KEY (patient_card_id) REFERENCES patient_card(id)
)