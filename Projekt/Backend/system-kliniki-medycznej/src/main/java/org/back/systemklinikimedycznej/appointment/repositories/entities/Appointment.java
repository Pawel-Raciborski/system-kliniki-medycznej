package org.back.systemklinikimedycznej.appointment.repositories.entities;

import jakarta.persistence.*;
import org.back.systemklinikimedycznej.appointment.domain.AppointmentStatus;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.calendar.DoctorCalendar;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name="calendar_id")
    private DoctorCalendar doctorCalendar;
    @ManyToOne
    @JoinColumn(name="patient_card_id")
    private PatientCard patientCard;
    @Column(name="visit_date_time")
    private String visitDateTime;
    @Column(name="finish_date_time")
    private String finishDateTime;
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
    @Column(name="diagnosis")
    private String diagnosis;

    @OneToMany(mappedBy = "appointment")
    private Set<AppointmentPrescription> appointmentPrescriptions;
}
