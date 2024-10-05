package org.back.systemklinikimedycznej.doctor.repositories.entities.calendar;

import jakarta.persistence.*;
import lombok.*;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@Builder
@Entity
@Table(name = "doctor_calendar")
@NoArgsConstructor
@AllArgsConstructor
public class DoctorCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    @OneToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToMany(mappedBy = "calendar")
    private Set<CalendarEvent> calendarEvents;

    @OneToMany(mappedBy = "doctorCalendar")
    private Set<Appointment> appointments;
}
