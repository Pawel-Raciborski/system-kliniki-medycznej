create table role_permission(
    id SERIAL NOT NULL PRIMARY KEY,
    role_id INT NOT NULL ,
    permission_id INT NOT NULL,
    constraint fk_role_permission_role FOREIGN KEY (role_id) REFERENCES role(id),
    constraint fk_role_permission_permission FOREIGN KEY (permission_id) REFERENCES permission(id)
);