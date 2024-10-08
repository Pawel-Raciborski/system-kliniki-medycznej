create table disease(
    id SERIAL NOT NULL PRIMARY KEY ,
    category_id int null,
    icd11code varchar(128),
    icd11title text,
    icd10code varchar(128),
    icd10title text,
    CONSTRAINT fk_disease_category FOREIGN KEY (category_id) REFERENCES category(id)
)