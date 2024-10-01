package org.back.systemklinikimedycznej.doctor.repositories.entities.calendar;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "calendar_events")
public class CalendarEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "event_name")
    private String eventName;
    @Column(name = "description")
    private String description;
    @Column(name = "start_date")
    private LocalDateTime startDateTime;
    @Column(name = "finish_date")
    private LocalDateTime finishDateTime;
    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private DoctorCalendar calendar;
}
