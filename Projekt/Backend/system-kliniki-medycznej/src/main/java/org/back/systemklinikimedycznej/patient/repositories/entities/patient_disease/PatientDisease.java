package org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease;

import jakarta.persistence.*;
import org.back.systemklinikimedycznej.patient.domain.CureStatus;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "patient_disease")
public class PatientDisease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "patient_card_id")
    private PatientCard patientCard;
    @Column(name = "disease_name")
    private String diseaseName;
    @Column(name = "description")
    private String description;
    @Column(name = "detection_date")
    private LocalDate detectionDate;
    @Column(name = "cure_status")
    @Enumerated(EnumType.STRING)
    private CureStatus cureStatus;
    @Column(name = "finish_date")
    private LocalDate finishCureDate;

    @OneToMany(mappedBy = "patientDisease")
    private Set<PatientDiseaseMedicine> patientDiseaseMedicines;
}
