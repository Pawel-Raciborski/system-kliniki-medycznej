create table medical_visit_prescription
(
    id              SERIAL not null PRIMARY KEY,
    prescription_id INT    NOT NULL,
    appointment_id  uuid, -- można wystawić receptę bez przeprowadzania wizyty
    CONSTRAINT fk_medical_visit_prescription_prescription FOREIGN KEY (prescription_id) REFERENCES prescription (id),
    CONSTRAINT fk_medical_visit_prescription_appointment FOREIGN KEY (appointment_id) REFERENCES appointment (id)
)