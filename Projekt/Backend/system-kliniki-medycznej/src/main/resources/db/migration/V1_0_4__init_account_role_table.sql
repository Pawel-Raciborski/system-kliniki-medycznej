create table account_role(
    id SERIAL NOT NULL PRIMARY KEY,
    role_id INT NOT NULL,
    account_id INT NOT NULL,
    CONSTRAINT fk_account_role_role FOREIGN KEY (role_id) REFERENCES role(id),
    CONSTRAINT fk_account_role_account FOREIGN KEY (account_id) REFERENCES account(id)
)