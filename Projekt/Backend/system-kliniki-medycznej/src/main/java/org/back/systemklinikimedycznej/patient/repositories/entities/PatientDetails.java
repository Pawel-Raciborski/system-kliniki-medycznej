package org.back.systemklinikimedycznej.patient.repositories.entities;

import jakarta.persistence.*;
import org.back.systemklinikimedycznej.patient.domain.BloodType;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;

@Entity
@Table(name = "patient_details")
public class PatientDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "weight_in_kg")
    private String weightInKg;
    @Column(name = "height_in_cm")
    private String heightInKg;
    @Column(name = "blood_type")
    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    @OneToOne(mappedBy = "patientDetails")
    private PatientCard patientCard;
}
