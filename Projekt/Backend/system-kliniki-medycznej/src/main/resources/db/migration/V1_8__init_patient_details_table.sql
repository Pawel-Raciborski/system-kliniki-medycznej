create table patient_details
(
    id           SERIAL NOT NULL PRIMARY KEY,
    weight_in_kg varchar(16),
    height_in_cm varchar(16),
    blood_type   varchar(32)
)