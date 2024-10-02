create table receptionist
(
    id                  SERIAL NOT NULL PRIMARY KEY,
    account_id             INT    NOT NULL,
    personal_details_id INT    NOT NULL,
    date_of_employment  DATE,
    CONSTRAINT fk_receptionist_personal_details FOREIGN KEY (personal_details_id) REFERENCES personal_details (id),
    CONSTRAINT fk_receptionist_user FOREIGN KEY (account_id) REFERENCES account (id)
);