package org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.back.systemklinikimedycznej.disease.repository.entities.Disease;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.patient.domain.CureStatus;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
@Entity
@Table(name = "patient_disease")
@NoArgsConstructor
@AllArgsConstructor
public class PatientDisease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "patient_card_id")
    private PatientCard patientCard;
    @ManyToOne
    @JoinColumn(name="disease_id")
    private Disease disease;
    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor detectedDoctor;
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
    private Set<Hospitalization> hospitalizations;
}
