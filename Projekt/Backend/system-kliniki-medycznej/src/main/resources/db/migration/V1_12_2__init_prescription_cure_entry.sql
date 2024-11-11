create table prescription_medicine_entry
(
    id              SERIAL       NOT NULL PRIMARY KEY,
    dosage          varchar(512) NOT NULL,
    medicine_id     int          NOT NULL,
    prescription_id uuid          NOT NULL,
    CONSTRAINT fk_prescription_medicine_entry_prescription FOREIGN KEY (prescription_id) REFERENCES prescription (id),
    CONSTRAINT fk_prescription_medicine_entry_medicine FOREIGN KEY (medicine_id) REFERENCES medicine (id)
)