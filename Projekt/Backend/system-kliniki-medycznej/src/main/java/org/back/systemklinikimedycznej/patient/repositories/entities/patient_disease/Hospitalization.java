package org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease;

import jakarta.persistence.*;
import org.back.systemklinikimedycznej.cure.repositories.entities.Medicine;

import java.time.LocalDate;

@Entity
@Table(name = "hospitalization")
public class Hospitalization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="patient_disease_id")
    private PatientDisease patientDisease;
    @ManyToOne
    @JoinColumn(name="medicine_id")
    private Medicine medicine;
    @Column(name="medicine_dosage")
    private String cureDosage;
    @Column(name="notes")
    private String notes;
    @Column(name="medicine_update_date")
    private LocalDate medicineUpdateDate;
}
