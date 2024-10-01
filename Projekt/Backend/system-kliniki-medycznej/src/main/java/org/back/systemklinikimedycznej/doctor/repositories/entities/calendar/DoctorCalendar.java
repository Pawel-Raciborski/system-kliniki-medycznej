package org.back.systemklinikimedycznej.doctor.repositories.entities.calendar;

import jakarta.persistence.*;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "doctor_calendar")
public class DoctorCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    @OneToMany(mappedBy = "calendar")
    private Set<CalendarEvent> calendarEvents;

    @OneToMany(mappedBy = "doctorCalendar")
    private Set<Appointment> appointments;
}
