package org.back.systemklinikimedycznej.prescription.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.back.systemklinikimedycznej.appointment.repositories.entities.AppointmentPrescription;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Entity
@Table(name = "prescription")
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    @Column(name="expiration_date")
    private LocalDate expirationDate;

    @Column(name="created_date")
    private LocalDate createdDate;

    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "prescription")
    private Set<PrescriptionMedicine> prescriptionMedicines;
    
    @OneToMany(mappedBy = "prescription")
    private Set<AppointmentPrescription> appointmentPrescriptions;
}
