CREATE table medicine
(
    id                             SERIAL NOT NULL PRIMARY KEY,
    api_medicine_id                    INT,
    specimen_type                  varchar(256),
    medicinal_product_name         varchar(1024),
    common_name                    varchar(1024),
    pharmaceutical_form_name       varchar(256),
    medicinal_product_power        varchar(512),
    active_substance_name          varchar(512),
    subject_medicinal_product_name varchar(512),
    registry_number                varchar(128),
    procedure_type_name            varchar(32),
    expiration_date_string         varchar(32),
    atc_code                       varchar(32),
    target_species                 varchar(32)
);