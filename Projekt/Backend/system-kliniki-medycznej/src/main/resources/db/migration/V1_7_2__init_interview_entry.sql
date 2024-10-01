create table interview_entry
(
    id             SERIAL NOT NULL PRIMARY KEY,
    question       text   NOT NULL,
    patient_answer text   NOT NULL,
    interview_id   INT    NOT NULL,
    CONSTRAINT fk_interview_entry_interview FOREIGN KEY (interview_id) REFERENCES interview (id)
);