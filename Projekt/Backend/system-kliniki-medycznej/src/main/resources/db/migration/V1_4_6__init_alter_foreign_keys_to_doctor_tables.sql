-- doctor table
alter table doctor
    add CONSTRAINT fk_doctor_personal_details FOREIGN KEY (personal_details_id) REFERENCES personal_details (id),
    add CONSTRAINT fk_doctor_user FOREIGN KEY (account_id) REFERENCES account (id);

-- doctor_calendar table
alter table doctor_calendar
    ADD CONSTRAINT fk_doctor_calendar FOREIGN KEY (doctor_id) REFERENCES doctor (id);

-- doctor_specialization
alter table doctor_specialization
    add CONSTRAINT fk_doctor_specialization_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (id);

-- doctor_office_hours
alter table doctor_office_hours
    add CONSTRAINT fk_doctor_office_hours_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (id);

-- calendar_event
alter table calendar_event
    add CONSTRAINT fk_calendar_event_calendar FOREIGN KEY (calendar_id) REFERENCES doctor_calendar (id);