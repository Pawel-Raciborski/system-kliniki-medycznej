create table calendar_event
(
    id          SERIAL       NOT NULL PRIMARY KEY,
    event_name  VARCHAR(128) NOT NULL,
    description TEXT,
    start_date  timestamp,
    finish_date timestamp,
    calendar_id INT          NOT NULL,
    CONSTRAINT fk_calendar_event_calendar FOREIGN KEY (calendar_id) REFERENCES doctor_calendar (id)
)