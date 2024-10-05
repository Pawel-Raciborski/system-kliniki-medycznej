package org.back.systemklinikimedycznej.doctor.repositories.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@With
@Getter
@Setter
@Builder
@Entity
@Table(name = "doctor_office_hours")
@NoArgsConstructor
@AllArgsConstructor
public class DoctorOfficeHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @Column(name="day")
    @Enumerated(EnumType.STRING)
    private DayOfWeek day;
    @Column(name="start_time")
    private LocalTime startHour;
    @Column(name="end_time")
    private LocalTime endHour;
    @Column(name="modified_date")
    private LocalDate modifiedDate;
}
