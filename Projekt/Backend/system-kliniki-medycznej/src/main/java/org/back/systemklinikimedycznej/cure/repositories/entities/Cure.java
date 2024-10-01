package org.back.systemklinikimedycznej.cure.repositories.entities;

import jakarta.persistence.*;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.PatientDiseaseCure;

import java.util.Set;

@Entity
@Table(name = "cure")
public class Cure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "cure")
    private Set<PatientDiseaseCure> patientDiseaseCures;
}
