package org.back.systemklinikimedycznej.prescription.repositories.entities;

import jakarta.persistence.*;
import org.back.systemklinikimedycznej.appointment.repositories.entities.AppointmentPrescription;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "prescription")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;
    @Column(name="date_of_issue")
    private LocalDate dateOfIssue;
    @Column(name="description")
    private String description;
    @OneToMany(mappedBy = "prescription")
    private Set<PrescriptionCure> prescriptionCures;
    
    @OneToMany(mappedBy = "prescription")
    private Set<AppointmentPrescription> appointmentPrescriptions;
}
