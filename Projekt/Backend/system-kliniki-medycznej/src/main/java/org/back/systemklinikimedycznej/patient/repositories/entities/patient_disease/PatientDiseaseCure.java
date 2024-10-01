package org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease;

import jakarta.persistence.*;
import org.back.systemklinikimedycznej.cure.repositories.entities.Cure;

import java.time.LocalDate;

@Entity
@Table(name = "patient_disease_cure")
public class PatientDiseaseCure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="patient_disease_id")
    private PatientDisease patientDisease;
    @ManyToOne
    @JoinColumn(name="cure_id")
    private Cure cure;
    @Column(name="cure_dosage")
    private String cureDosage;
    @Column(name="notes")
    private String notes;
    @Column(name="cure_update_date")
    private LocalDate cureUpdateDate;
}
