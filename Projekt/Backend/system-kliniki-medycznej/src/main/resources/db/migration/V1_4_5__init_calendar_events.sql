create table calendar_event
(
    id               SERIAL       NOT NULL PRIMARY KEY,
    event_name       VARCHAR(128) NOT NULL,
    description      TEXT,
    start_date_time  timestamp,
    finish_date_time timestamp,
    calendar_id      INT          NOT NULL
)