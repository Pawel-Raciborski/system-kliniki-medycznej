package org.back.systemklinikimedycznej.appointment.repositories.entities;

import jakarta.persistence.*;
import org.back.systemklinikimedycznej.prescription.repositories.entities.Prescription;

@Entity
@Table(name = "appointment_prescription")
public class AppointmentPrescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;
    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}
