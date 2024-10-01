create table patient_details
(
    id           SERIAL NOT NULL PRIMARY KEY,
    weight_in_kg numeric(6, 2),
    height_in_cm numeric(5, 2),
    blood_type   varchar(32)
)