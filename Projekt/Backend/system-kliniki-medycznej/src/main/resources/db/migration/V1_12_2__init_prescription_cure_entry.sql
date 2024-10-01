create table prescription_cure_entry
(
    id              SERIAL       NOT NULL PRIMARY KEY,
    dosage          varchar(512) NOT NULL,
    cure            varchar(512) NOT NULL,
    prescription_id INT          NOT NULL,
    CONSTRAINT fk_prescription_cure_entry_prescription FOREIGN KEY (prescription_id) REFERENCES prescription (id)
)